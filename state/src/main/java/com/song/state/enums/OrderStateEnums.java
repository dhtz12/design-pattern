package com.song.state.enums;

/**
 * packageName:com.song.state.enums
 * User: lss
 * Date: 2023/6/11
 * desc:
 */
public enum OrderStateEnums {
    UNPAID( "未支付", 0 ), PAID( "已支付", 1 ), DELIVERED( "已发货", 2 ), RECEIVED( "已妥投", 3 ), DELETED( "已删除", 4 );

    private String name;
    private Integer code;

    OrderStateEnums(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
