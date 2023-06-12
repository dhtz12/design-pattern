package com.song.state;

import com.song.state.service.IOrderService;
import com.song.state.vo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StateApplicationTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void testPay() {
        Order order = orderService.create();
        orderService.pay( order );
        orderService.deliver( order );
        orderService.receive( order );
    }

}
