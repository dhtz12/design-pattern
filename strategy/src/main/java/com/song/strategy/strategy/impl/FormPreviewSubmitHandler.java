package com.song.strategy.strategy.impl;

import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import com.song.strategy.strategy.FormSubmitHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class FormPreviewSubmitHandler implements FormSubmitHandler<Serializable> {


    @Override
    public String getSubmitType() { return "preview"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        log.info("预览模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        return CommonPairResponse.success("预览模式提交数据成功！");
    }
}
