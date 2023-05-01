# design-patterns/README.MD

## 1.event-design-pattern
   
   事件设计模式，主要包含三个类，分别是事件Publisher、事件监听器listener、事件对象event。
   publisher主要负责事件的注册、触发、移除等功能。
      
      void registerListener(EventListener listener);

      void removeListener(EventListener listener);

      void notifyListener(AbstractEvent event);

   listener主要负责事件的回调处理

    public void onEvent(AbstractEvent event);


   event主要负责事件的传递，包含事件的类型、事件的源对象、事件的目标对象等信息。
    
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderPayEvent(String name,String orderId) {
        super(name);
        this.orderId = orderId;
    }

   demo中例子主要是关于订单创建成功和支付成功后发送邮件和短信功能的事件模式的实现

   - event/normal 文件夹内是一个简单的事件设计模式的实现包含了事件的注册、触发、移除等功能。
   
   - event/spring 文件夹内是利用spring的ApplicationEventPublisher和ApplicationListener实现的事件设计模式。  
   > spring 模式中有两种实现方式，一种是继承ApplicationListener，一种是注解@EventListener。  
   > 只需要在监听器的回调方法onApplicationEvent() 上加上 @Async 注解就可以了。（启动类上需要添加 @EnableAsync 注解开启异步调用
   