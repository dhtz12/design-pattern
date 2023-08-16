### **一、什么是责任链模式？**

责任链模式（Chain of Responsibility Pattern），顾名思义，为请求者和接受者之间创建一条对象处理链路，避免请求发送者与接收者耦合在一起！

![image-20230816110753811](责任链模式.assets/image-20230816110753811.png)


责任链模式，是一种实用性非常强的设计模式，比较典型的应用场景有：

- Apache Tomcat 对 Encoding 编码处理的处理
- SpringBoot ⾥⾯的拦截器、过滤器链
- netty 中的处理链
- 支付风控的机制
- ⽇志处理级别

尤其是当程序的处理流程很长的时候，采用责任链设计模式，不仅实现优雅，而且易复用可扩展！

今天我们就一起来了解一下在 SpringBoot 中如何应用责任链模式！



### **二、代码实践**

我们以某下单流程为例，将其切成多个独立检查逻辑，可能会经过的数据验证处理流程如下：

![image-20230816110811334](责任链模式.assets/image-20230816110811334.png)



采用责任链设计模式来编程，代码实践如下！

```
/**
 * order handler intercept.
 * @author z004dtzc
 */
public interface OrderHandlerIntercept {

    /**
     * order context.
     *
     * @param context context
     * @return order context
     */
    OrderContext handle(OrderContext context);

}

// 重复订单
@Component
@Slf4j
@Order(1)
public class RepeatOrderHandlerIntercept implements OrderHandlerIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        log.info("check weather repeat order");
        return context;
    }
}
//参数是否包含空
@Component
@Slf4j
@Order(2)
public class ParamOrderHandlerIntercept implements OrderHandlerIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        log.info("check weather contains blank param");
        return context;
    }
}

// 余额是否充足
@Component
@Slf4j
@Order(3)
public class BankOrderHandlerIntercept implements OrderHandlerIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        log.info("check weather bank is enough");
        return context;
    }
}


// 责任链的chain
@Component
@Slf4j
public class OrderInterceptChainService {

    private final List<OrderHandlerIntercept> handlerList;

    public OrderInterceptChainService(final List<OrderHandlerIntercept> handlerList) {
        this.handlerList = handlerList;
    }


    public OrderContext execute(OrderContext orderContext) {

        for (OrderHandlerIntercept handlerIntercept : handlerList) {
            orderContext = handlerIntercept.handle(orderContext);
        }
        return orderContext;
    }

}

// 测试类
@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    private OrderInterceptChainService orderInterceptChainService;

    @Test
    public void test() {
        orderInterceptChainService.execute(new OrderContext());
    }

}
```

