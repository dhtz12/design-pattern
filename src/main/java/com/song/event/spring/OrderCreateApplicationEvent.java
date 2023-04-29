package com.song.event.spring;

import org.springframework.context.ApplicationEvent;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
public class OrderCreateApplicationEvent extends ApplicationEvent {

      private String orderId;

      public String getOrderId() {
         return orderId;
      }

      public OrderCreateApplicationEvent(Object source, String orderId) {
         super(source);
         this.orderId = orderId;
      }
}
