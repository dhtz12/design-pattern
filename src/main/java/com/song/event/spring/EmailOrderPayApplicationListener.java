package com.song.event.spring;

import org.springframework.context.ApplicationListener;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
//@Component
public class EmailOrderPayApplicationListener implements ApplicationListener<OrderPayApplicationEvent> {

    @Override
    public void onApplicationEvent(OrderPayApplicationEvent event) {
        System.out.println("发送邮件成功".concat( event.getOrderId()));
    }
}
