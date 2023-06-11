package com.song.event.spring;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void pay(String orderId) {
        System.out.println( "订单支付成功" );
        applicationEventPublisher.publishEvent( new OrderPayApplicationEvent( this, orderId ) );
    }

    @Override
    public void create(String orderId) {
         System.out.println( "订单创建成功" );
         applicationEventPublisher.publishEvent( new OrderCreateApplicationEvent( this, orderId ) );
    }
}
