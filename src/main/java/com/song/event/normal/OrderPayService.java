package com.song.event.normal;


/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc:
 */
public class OrderPayService {
    private final EventPublisher eventPublisher;

    public OrderPayService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void pay(String orderId) {
        System.out.println( "订单支付成功" );
        eventPublisher.notifyListener( new OrderPayEvent( "orderName", orderId ) );
    }
}
