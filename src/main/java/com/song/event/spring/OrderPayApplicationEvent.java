package com.song.event.spring;

import org.springframework.context.ApplicationEvent;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 * @author lss
 */
public class OrderPayApplicationEvent extends ApplicationEvent {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public OrderPayApplicationEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

}

