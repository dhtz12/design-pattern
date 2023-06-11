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
public class PayOrderStateServiceImpl extends OrderAbstractStateServiceImpl {


    @Override
    public void pay(Order order) {
        order.setStateEnums( OrderStateEnums.PAID );
        System.out.println( "PayOrderStateServiceImpl.pay = " + order );
    }

    @Override
    public boolean supportState(OrderStateEnums orderStateEnums) {
        return OrderStateEnums.UNPAID.getCode().equals( orderStateEnums.getCode() );
    }
}
