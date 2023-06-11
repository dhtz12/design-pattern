package com.song.event.spring;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
public interface IOrderService {

      void pay(String orderId);

      void create(String orderId);
}
