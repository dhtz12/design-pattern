package com.song.responsibility;

import com.song.responsibility.OrderIntercept.OrderInterceptChainService;
import com.song.responsibility.entity.OrderContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    private OrderInterceptChainService orderInterceptChainService;

    @Test
    public void test() {
        orderInterceptChainService.execute(new OrderContext());
    }

}