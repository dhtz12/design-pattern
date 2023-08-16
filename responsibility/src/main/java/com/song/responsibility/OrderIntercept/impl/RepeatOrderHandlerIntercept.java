package com.song.responsibility.OrderIntercept.impl;

import com.song.responsibility.entity.OrderContext;
import com.song.responsibility.OrderIntercept.OrderHandlerIntercept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class RepeatOrderHandlerIntercept implements OrderHandlerIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        log.info("check weather repeat order");
        return context;
    }
}
