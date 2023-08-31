package com.song.strategy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import com.song.strategy.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class SimpleController {

    @Autowired
    private IFormService formService;

    @PostMapping("/form/submit")
    public CommonPairResponse<String, Serializable> submitForm(@RequestParam String submitType,
                                                               @RequestParam String formInputJson) {
        JSONObject formInput = JSON.parseObject(formInputJson);

        FormSubmitRequest request = new FormSubmitRequest();
        request.setUserId(123456L);
        request.setSubmitType(submitType);
        request.setFormInput(formInput);

        return formService.submitForm(request);
    }
}
