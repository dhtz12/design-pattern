package com.song.state.service;

import com.song.state.enums.OrderStateEnums;
import com.song.state.vo.Order;

/**
 * packageName:com.song.state.service
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
public interface IOrderStateService {


    /**
     * 支付订单
     */
    void pay(Order order);

    /**
     * 发货
     */
    void deliver(Order order);

    /**
     * 完成订单
     */
    void receive(Order order);

    /**
     * 支持状态
     */
    boolean supportState(OrderStateEnums orderStateEnums);
}
