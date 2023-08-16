package com.song.responsibility.OrderIntercept.impl;

import com.song.responsibility.entity.OrderContext;
import com.song.responsibility.OrderIntercept.OrderHandlerIntercept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(2)
public class ParamOrderHandlerIntercept implements OrderHandlerIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        log.info("check weather contains blank param");
        return context;
    }
}
