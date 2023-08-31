



# 策略模式（Strategy Pattern）

定义了一组策略，分别在不同类中封装起来，每种策略都可以根据当前场景相互替换，从而使策略的变化可以独立于操作者。比如我们要去某个地方，会根据距离的不同（或者是根据手头经济状况）来选择不同的出行方式（共享单车、坐公交、滴滴打车等等），这些出行方式即不同的策略。

阿里开发规约-编程规约-控制语句-第六条 ：超过 3 层的 if-else 的逻辑判断代码可以使用卫语句、策略模式、状态模式等来实现。相信大家都见过这种代码：

```
if (conditionA) {
    逻辑1
} else if (conditionB) {
    逻辑2
} else if (conditionC) {
    逻辑3
} else {
    逻辑4
}
```

这种代码虽然写起来简单，但是很明显违反了面向对象的 2 个基本原则：

- 单一职责原则（一个类应该只有一个发生变化的原因）：因为之后修改任何一个逻辑，当前类都会被修改
- 开闭原则（对扩展开放，对修改关闭）：如果此时需要添加（删除）某个逻辑，那么不可避免的要修改原来的代码

因为违反了以上两个原则，尤其是当 if-else 块中的代码量比较大时，后续代码的扩展和维护就会逐渐变得非常困难且容易出错，使用卫语句也同样避免不了以上两个问题。因此根据我的经验，得出一个我个人认为比较好的实践：

- if-else 不超过 2 层，块中代码 1~5 行，直接写到块中，否则封装为方法
- if-else 超过 2 层，但块中的代码不超过 3 行，尽量使用卫语句
- if-else 超过 2 层，且块中代码超过 3 行，尽量使用策略模式

### 需求背景

我们平台的动态表单，之前专门用于模型输入的提交。现在业务方希望对表单能力进行开放，除了可用于模型提交，还可以用于业务方指定功能的提交（方式设计为绑定一个 HSF 泛化服务，HSF 即淘系内部的 RPC 框架）。加上我们在配置表单时的 “预览模式” 下的提交，那么表单目前便有以下三种提交类型：

我们平台的动态表单，之前专门用于模型输入的提交。现在业务方希望对表单能力进行开放，除了可用于模型提交，还可以用于业务方指定功能的提交（方式设计为绑定一个 HSF 泛化服务，HSF 即淘系内部的 RPC 框架）。加上我们在配置表单时的 “预览模式” 下的提交，那么表单目前便有以下三种提交类型：

- 预览表单时的提交
- 模型输入时的提交
- 绑定 HSF 时的提交

现在，有请我的 “最佳套路” 上场

### 第一步，定义策略接口

首先定义策略的接口，包括两个方法：

1、获取策略类型的方法

2、处理策略逻辑的方法

```
/**
 * 表单提交处理器
 */
public interface FormSubmitHandler<R extends Serializable> {

    /**
     * 获得提交类型（返回值也可以使用已经存在的枚举类）
     *
     * @return 提交类型
     */
    String getSubmitType();

    /**
     * 处理表单提交请求
     *
     * @param request 请求
     * @return 响应，left 为返回给前端的提示信息，right 为业务值
     */
    CommonPairResponse<String, R> handleSubmit(FormSubmitRequest request);
}
```

```
/**
 * 表单提交的请求
 */
@Getter
@Setter
public class FormSubmitRequest {

    /**
     * 提交类型
     *
     * @see FormSubmitHandler#getSubmitType()
     */
    private String submitType;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 表单提交的值
     */
    private Map<String, Object> formInput;

    // 其他属性
}
```

其中，FormSubmitHandler 的 getSubmitType  方法用来获取表单的提交类型（即策略类型），用于根据客户端传递的参数直接获取到对应的策略实现；客户端传递的相关参数都被封装为  FormSubmitRequest，传递给 handleSubmit 进行处理。

### 第二步，相关策略实现



#### 预览表单时的提交

```
@Component
public class FormPreviewSubmitHandler implements FormSubmitHandler<Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "preview"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("预览模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        return CommonPairResponse.success("预览模式提交数据成功！", null);
    }
}
```

#### 模型输入时的提交

```
@Component
public class FormModelSubmitHandler implements FormSubmitHandler<Long> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "model"; }

    @Override
    public CommonPairResponse<String, Long> handleSubmit(FormSubmitRequest request) {
        logger.info("模型提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 模型创建成功后获得模型的 id
        Long modelId = createModel(request);

        return CommonPairResponse.success("模型提交成功！", modelId);
    }

    private Long createModel(FormSubmitRequest request) {
        // 创建模型的逻辑
        return 123L;
    }
}
```

#### HSF 模式的提交

```
@Component
public class FormHsfSubmitHandler implements FormSubmitHandler<Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "hsf"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("HSF 模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 进行 HSF 泛化调用，获得业务方返回的提示信息和业务数据
        CommonPairResponse<String, Serializable> response = hsfSubmitData(request);

        return response;
    }

    ...
}
```

### 第三步，建立策略的简单工厂

```
@Component
public class FormSubmitHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final
    Map<String, FormSubmitHandler<Serializable>> FORM_SUBMIT_HANDLER_MAP = new HashMap<>(8);

    private ApplicationContext appContext;

    /**
     * 根据提交类型获取对应的处理器
     *
     * @param submitType 提交类型
     * @return 提交类型对应的处理器
     */
    public FormSubmitHandler<Serializable> getHandler(String submitType) {
        return FORM_SUBMIT_HANDLER_MAP.get(submitType);
    }

    @Override
    public void afterPropertiesSet() {
        // 将 Spring 容器中所有的 FormSubmitHandler 注册到 FORM_SUBMIT_HANDLER_MAP
        appContext.getBeansOfType(FormSubmitHandler.class)
                  .values()
                  .forEach(handler -> FORM_SUBMIT_HANDLER_MAP.put(handler.getSubmitType(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}
```

我们让 FormSubmitHandlerFactory 实现 InitializingBean 接口，在 afterPropertiesSet 方法中，基于 Spring 容器将所有 FormSubmitHandler 自动注册到 FORM_SUBMIT_HANDLER_MAP，从而  Spring 容器启动完成后， getHandler 方法可以直接通过 submitType 来获取对应的表单提交处理器。

### 第四步，使用 & 测试

在表单服务中，我们通过 FormSubmitHandlerFactory 来获取对应的表单提交处理器，从而处理不同类型的提交：

```
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormSubmitHandlerFactory submitHandlerFactory;

    public CommonPairResponse<String, Serializable> submitForm(@NonNull FormSubmitRequest request) {
        String submitType = request.getSubmitType();

        // 根据 submitType 找到对应的提交处理器
        FormSubmitHandler<Serializable> submitHandler = submitHandlerFactory.getHandler(submitType);

        // 判断 submitType 对应的 handler 是否存在
        if (submitHandler == null) {
            return CommonPairResponse.failure("非法的提交类型: " + submitType);
        }

        // 处理提交
        return submitHandler.handleSubmit(request);
    }
}
```

**Factory 只负责获取 Handler，Handler 只负责处理具体的提交，Service 只负责逻辑编排，从而达到功能上的 “低耦合高内聚”。**

```
@RestController
public class SimpleController {

    @Autowired
    private FormService formService;

    @PostMapping("/form/submit")
    public CommonPairResponse<String, Serializable> submitForm(@RequestParam String submitType,
                                                               @RequestParam String formInputJson) {
        JSONObject formInput = JSON.parseObject(formInputJson);

        FormSubmitRequest request = new FormSubmitRequest();
        request.setUserId(123456L);
        request.setSubmitType(submitType);
        request.setFormInput(formInput);

        return formService.submitForm(request);
    }
}
```

引用地址：
https://www.infoq.cn/article/6gczx3kg3gfj5yfx08mm