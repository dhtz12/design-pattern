package com.song.state.service;

import com.song.state.vo.Order;

/**
 * packageName:com.song.state
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
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
