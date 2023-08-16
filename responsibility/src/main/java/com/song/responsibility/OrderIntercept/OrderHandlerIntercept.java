package com.song.responsibility.OrderIntercept;

import com.song.responsibility.entity.OrderContext;

/**
 * order handler intercept.
 * @author z004dtzc
 */
public interface OrderHandlerIntercept {

    /**
     * order context.
     *
     * @param context context
     * @return order context
     */
    OrderContext handle(OrderContext context);

}
