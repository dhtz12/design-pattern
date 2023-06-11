package com.song.state.vo;

import com.song.state.enums.OrderStateEnums;
import lombok.Builder;
import lombok.Data;

/**
 * packageName:com.song.state.vo
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
@Data
@Builder
public class Order {

    private Long orderId;

    private OrderStateEnums stateEnums;
}
