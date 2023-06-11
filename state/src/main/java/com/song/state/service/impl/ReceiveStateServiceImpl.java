package com.song.state.service.impl;

import com.song.state.enums.OrderStateEnums;
import com.song.state.vo.Order;
import org.springframework.stereotype.Service;

/**
 * packageName:com.song.state.service.impl
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
@Service
public class ReceiveStateServiceImpl extends OrderAbstractStateServiceImpl {


    @Override
    public void receive(Order order) {
        order.setStateEnums( OrderStateEnums.RECEIVED );
        System.out.println( "ReceiveStateServiceImpl.receive=" + order );
    }

    @Override
    public boolean supportState(OrderStateEnums orderStateEnums) {
        return OrderStateEnums.DELIVERED.getCode().equals( orderStateEnums.getCode() );
    }
}
