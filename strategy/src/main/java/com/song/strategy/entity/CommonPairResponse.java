package com.song.strategy.entity;

import lombok.Data;

@Data
public class CommonPairResponse<T, R> {


    /**
     * response message.
     */
    private String message;



    public static  CommonPairResponse success(String message) {
        CommonPairResponse responseResult = new CommonPairResponse();
        responseResult.setMessage(message);
        return responseResult;
    }

    public static  CommonPairResponse failure(String message) {
        CommonPairResponse responseResult = new CommonPairResponse();
        responseResult.setMessage(message);
        return responseResult;
    }
}
