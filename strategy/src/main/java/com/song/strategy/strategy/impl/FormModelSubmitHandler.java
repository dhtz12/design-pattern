package com.song.strategy.strategy.impl;

import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import com.song.strategy.strategy.FormSubmitHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FormModelSubmitHandler implements FormSubmitHandler<Long> {


    @Override
    public String getSubmitType() { return "model"; }

    @Override
    public CommonPairResponse<String, Long> handleSubmit(FormSubmitRequest request) {
        log.info("模型提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 模型创建成功后获得模型的 id
        Long modelId = createModel(request);

        return CommonPairResponse.success("模型提交成功");
    }

    private Long createModel(FormSubmitRequest request) {
        // 创建模型的逻辑
        return 123L;
    }
}
