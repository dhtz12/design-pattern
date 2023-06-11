package com.song.state.service.impl;

import com.song.state.service.IOrderStateService;
import com.song.state.vo.Order;
import org.springframework.stereotype.Service;

/**
 * packageName:com.song.state.service.impl
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
@Service
public abstract class OrderAbstractStateServiceImpl implements IOrderStateService {

    @Override
    public void pay(Order order) {
        System.out.println( "OrderAbstractStateServiceImpl.pay" );
        throw new UnsupportedOperationException( "不支持支付操作" );
    }

    @Override
    public void deliver(Order order) {
        System.out.println( "OrderAbstractStateServiceImpl.deliver" );
        throw new UnsupportedOperationException( "不支持发货操作" );
    }

    @Override
    public void receive(Order order) {
        System.out.println( "OrderAbstractStateServiceImpl.receive" );
        throw new UnsupportedOperationException( "不支持收货操作" );
    }
}
