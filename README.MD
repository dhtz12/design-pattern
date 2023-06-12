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
   

## 2. state-design-pattern
> 状态模式，包含三种角色，分别是状态服务方角色，状态客户方角色，特定状态服务方角色，状态模式的宗旨是通过特定状态服务方，将原来属于状态服务方的多状态模式，转化为单状态模式，降低了状态服务的逻辑复杂度，提高了状态服务的可扩展性。

> 抽象下面5个类，分别是订单状态服务接口，订单状态服务抽象类，支付订单状态服务类，已发货订单状态服务类，收货订单状态服务类。
> OrderService 类调用状态接口类，处理对应的逻辑
~~~
// IOrderStateService：订单状态服务接口 
// AbstractOrderStateService：订单状态服务抽象类
// PayOrderStateService：支付订单状态服务类
// DeliveredOrderStateService：已发货订单状态服务类
// ReceiverOrderStateService：已收货订单状态服务类
~~~

1. OrderService：订单状态服务接口
~~~java
public interface IOrderService {

      /**
      * 创建订单
      */
      Order create();

      /**
      * 支付订单
      */
      Order pay(Order order);

      /**
      * 发货
      */
      Order deliver(Order order);

      /**
      * 完成订单
      */
      Order receive(Order order);
}


@Service
public class OrderServiceImpl implements IOrderService {

   private final List<IOrderStateService> orderStateServiceList;

   private Long BASE_ORDER_ID = 1L;

   public OrderServiceImpl(List<IOrderStateService> orderStateServiceList1) {
      this.orderStateServiceList = orderStateServiceList1;
   }


   @Override
   public Order create() {
      long orderId = BASE_ORDER_ID++;
      Order order = Order.builder().orderId(BASE_ORDER_ID++).stateEnums(OrderStateEnums.UNPAID)
              .build();
      System.out.println("订单创建成功，订单号为：" + orderId);
      return order;
   }

   @Override
   public Order pay(Order order) {
      orderStateServiceList.stream()
              .filter(orderStateService -> orderStateService.supportState(order.getStateEnums()))
              .findFirst().orElseThrow(() -> new RuntimeException("没有找到对应的状态")).pay(order);
      return order;
   }

   @Override
   public Order deliver(Order order) {
      orderStateServiceList.stream()
              .filter(iOrderStateService -> iOrderStateService.supportState(order.getStateEnums()))
              .findFirst().orElseThrow(() -> new RuntimeException("没有找到对应的状态")).deliver(order);
      return order;
   }

   @Override
   public Order receive(Order order) {
      orderStateServiceList.stream()
              .filter(iOrderStateService -> iOrderStateService.supportState(order.getStateEnums()))
              .findFirst().orElseThrow(() -> new RuntimeException("没有找到对应的状态")).receive(order);
      return order;
   }
}
~~~
2. orderStateService：订单状态服务抽象类
~~~java
public interface IOrderStateService {


    /**
     * 支付订单
     */
    void pay(Order order);

    /**
     * 发货
     */
    void deliver(Order order);

    /**
     * 完成订单
     */
    void receive(Order order);

    /**
     * 支持状态
     */
    boolean supportState(OrderStateEnums orderStateEnums);
}

@Service
public abstract class OrderAbstractStateServiceImpl implements IOrderStateService {

   @Override
   public void pay(Order order) {
      System.out.println( "OrderAbstractStateServiceImpl.pay" );
      throw new UnsupportedOperationException( "不支持支付操作" );
   }

   @Override
   public void deliver(Order order) {
      System.out.println( "OrderAbstractStateServiceImpl.deliver" );
      throw new UnsupportedOperationException( "不支持发货操作" );
   }

   @Override
   public void receive(Order order) {
      System.out.println( "OrderAbstractStateServiceImpl.receive" );
      throw new UnsupportedOperationException( "不支持收货操作" );
   }
}

@Service
public class PayOrderStateServiceImpl extends OrderAbstractStateServiceImpl {


   @Override
   public void pay(Order order) {
      order.setStateEnums( OrderStateEnums.PAID );
      System.out.println( "PayOrderStateServiceImpl.pay = " + order );
   }

   @Override
   public boolean supportState(OrderStateEnums orderStateEnums) {
      return OrderStateEnums.UNPAID.getCode().equals( orderStateEnums.getCode() );
   }
}

@Service
public class DeliverStateServiceImpl extends OrderAbstractStateServiceImpl {
   @Override
   public void deliver(Order order) {
      order.setStateEnums( OrderStateEnums.DELIVERED);
      System.out.println( "DeliverStateServiceImpl.deliver = " + order );
   }

   @Override
   public boolean supportState(OrderStateEnums stateEnums) {
      return OrderStateEnums.PAID.getCode().equals( stateEnums.getCode() );
   }
}

@Service
public class ReceiverStateServiceImpl extends OrderAbstractStateServiceImpl {


   @Override
   public void receive(Order order) {
      order.setStateEnums( OrderStateEnums.RECEIVED );
      System.out.println( "ReceiveStateServiceImpl.receive=" + order );
   }

   @Override
   public boolean supportState(OrderStateEnums orderStateEnums) {
      return OrderStateEnums.DELIVERED.getCode().equals( orderStateEnums.getCode() );
   }
}
~~~

