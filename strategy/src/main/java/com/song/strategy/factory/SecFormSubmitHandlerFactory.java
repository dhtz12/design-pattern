package com.song.strategy.factory;

import com.song.strategy.strategy.FormSubmitHandler;

import java.io.Serializable;
import java.util.List;

public class SecFormSubmitHandlerFactory {

    private final List<FormSubmitHandler<Serializable>> formSubmitHandlerList;

    public SecFormSubmitHandlerFactory(List<FormSubmitHandler<Serializable>> formSubmitHandlers) {
        formSubmitHandlerList = formSubmitHandlers;
    }

    /**
     * 根据提交类型获取对应的处理器
     *
     * @param submitType 提交类型
     * @return 提交类型对应的处理器
     */
    public FormSubmitHandler<Serializable> getHandler(String submitType) {

        return formSubmitHandlerList.stream()
                .filter(formSubmitHandler -> formSubmitHandler.getSubmitType().equals(submitType)).findAny().orElseThrow(IllegalArgumentException::new);
    }

}
