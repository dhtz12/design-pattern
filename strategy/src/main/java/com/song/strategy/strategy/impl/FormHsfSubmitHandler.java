package com.song.strategy.strategy.impl;

import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import com.song.strategy.strategy.FormSubmitHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class FormHsfSubmitHandler implements FormSubmitHandler<Serializable> {

    @Override
    public String getSubmitType() { return "hsf"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        log.info("HSF 模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 进行 HSF 泛化调用，获得业务方返回的提示信息和业务数据
        CommonPairResponse<String, Serializable> response = null;

        return response;
    }

}
