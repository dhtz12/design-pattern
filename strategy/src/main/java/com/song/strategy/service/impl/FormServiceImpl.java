package com.song.strategy.service.impl;

import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import com.song.strategy.factory.FormSubmitHandlerFactory;
import com.song.strategy.strategy.FormSubmitHandler;
import com.song.strategy.service.IFormService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FormServiceImpl implements IFormService {

    private FormSubmitHandlerFactory submitHandlerFactory;

    public FormServiceImpl(FormSubmitHandlerFactory submitHandlerFactory) {
        this.submitHandlerFactory = submitHandlerFactory;
    }

    @Override
    public CommonPairResponse<String, Serializable> submitForm(@NonNull FormSubmitRequest request) {
        String submitType = request.getSubmitType();

        // 根据 submitType 找到对应的提交处理器
        FormSubmitHandler<Serializable> submitHandler = submitHandlerFactory.getHandler(submitType);

        // 判断 submitType 对应的 handler 是否存在
        if (submitHandler == null) {
            return CommonPairResponse.failure("非法的提交类型: " + submitType);
        }

        // 处理提交
        return submitHandler.handleSubmit(request);
    }
}
