package com.song.event.spring;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
@Component
public class OrderApplicationListener {

    @EventListener
    public void orderPaySmsApplicationListener(OrderPayApplicationEvent event) {
        System.out.println( "订单支付成功，发送短信".concat( event.getOrderId() ) );
    }
    @Async
    @EventListener
    public void orderCreateSmsApplicationListener(OrderCreateApplicationEvent event) {
        System.out.println( "订单创建成功，发送短信".concat( event.getOrderId() ) );
    }

    @EventListener
    public void orderPayEmailApplicationListener(OrderPayApplicationEvent event) {
        System.out.println( "订单支付成功，发送邮件".concat( event.getOrderId() ) );
    }
    @EventListener
      public void orderCreateEmailApplicationListener(OrderCreateApplicationEvent event) {
         System.out.println( "订单创建成功，发送邮件".concat( event.getOrderId() ) );
      }
}

