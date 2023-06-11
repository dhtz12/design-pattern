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
public class DeliverStateServiceImpl extends OrderAbstractStateServiceImpl {
    @Override
    public void deliver(Order order) {
        order.setStateEnums( OrderStateEnums.DELIVERED);
        System.out.println( "DeliverStateServiceImpl.deliver = " + order );
    }

    @Override
    public boolean supportState(OrderStateEnums stateEnums) {
        return OrderStateEnums.PAID.getCode().equals( stateEnums.getCode() );
    }
}
