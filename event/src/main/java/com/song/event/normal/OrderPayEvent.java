package com.song.event.normal;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc:
 */
public class OrderPayEvent extends AbstractEvent {

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

}
