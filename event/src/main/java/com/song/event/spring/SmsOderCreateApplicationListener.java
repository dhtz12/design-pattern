package com.song.event.spring;

import org.springframework.context.ApplicationListener;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 * @author lss
 */
//@Component
public class SmsOderCreateApplicationListener implements ApplicationListener<OrderCreateApplicationEvent> {

    @Override
    public void onApplicationEvent(OrderCreateApplicationEvent event) {
        System.out.println("订单创建，发送短信成功".concat( event.getOrderId()));
    }
}
