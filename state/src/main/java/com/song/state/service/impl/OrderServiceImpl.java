package com.song.state.service.impl;

import com.song.state.enums.OrderStateEnums;
import com.song.state.service.IOrderService;
import com.song.state.service.IOrderStateService;
import com.song.state.vo.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName:com.song.state.service.impl
 * User: lss
 * Date: 2023/6/11
 * desc:
 */

@Service
public class OrderServiceImpl implements IOrderService {

    private final List<IOrderStateService> orderStateServiceList;

    private Long BASE_ORDER_ID = 1L;

    public OrderServiceImpl(List<IOrderStateService> orderStateServiceList1) {
        this.orderStateServiceList = orderStateServiceList1;
    }


    @Override
    public Order create() {
        long orderId = BASE_ORDER_ID++;
        Order order = Order.builder().orderId( BASE_ORDER_ID++ ).stateEnums( OrderStateEnums.UNPAID ).build();
        System.out.println( "订单创建成功，订单号为：" + orderId );
        return order;
    }

    @Override
    public Order pay(Order order) {
        orderStateServiceList.stream().filter( orderStateService -> orderStateService.supportState( order.getStateEnums() ) ).findFirst().orElseThrow( () -> new RuntimeException( "没有找到对应的状态" ) ).pay( order );
        return order;
    }

    @Override
    public Order deliver(Order order) {
        orderStateServiceList.stream().filter( iOrderStateService -> iOrderStateService.supportState( order.getStateEnums() ) ).findFirst()
                .orElseThrow( () -> new RuntimeException( "没有找到对应的状态" ) ).deliver( order );
        return order;
    }

    @Override
    public Order receive(Order order) {
        orderStateServiceList.stream().filter( iOrderStateService -> iOrderStateService.supportState( order.getStateEnums() ) ).findFirst()
                .orElseThrow( () -> new RuntimeException( "没有找到对应的状态" ) ).receive( order );
        return order;
    }
}
