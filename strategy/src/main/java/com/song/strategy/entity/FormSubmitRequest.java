package com.song.strategy.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 表单提交的请求
 */
@Getter
@Setter
public class FormSubmitRequest {

    /**
     * 提交类型
     *
     * @see FormSubmitHandler#getSubmitType()
     */
    private String submitType;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 表单提交的值
     */
    private Map<String, Object> formInput;

    // 其他属性
}
