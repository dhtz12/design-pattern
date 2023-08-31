package com.song.strategy.service;

import com.song.strategy.entity.CommonPairResponse;
import com.song.strategy.entity.FormSubmitRequest;
import lombok.NonNull;

import java.io.Serializable;

public interface IFormService {
    CommonPairResponse<String, Serializable> submitForm(@NonNull FormSubmitRequest request);
}
