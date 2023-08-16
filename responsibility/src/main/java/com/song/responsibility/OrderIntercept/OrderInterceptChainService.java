package com.song.responsibility.OrderIntercept;

import com.song.responsibility.entity.OrderContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderInterceptChainService {

    private final List<OrderHandlerIntercept> handlerList;

    public OrderInterceptChainService(final List<OrderHandlerIntercept> handlerList) {
        this.handlerList = handlerList;
    }


    public OrderContext execute(OrderContext orderContext) {

        for (OrderHandlerIntercept handlerIntercept : handlerList) {
            orderContext = handlerIntercept.handle(orderContext);
        }
        return orderContext;
    }

}
