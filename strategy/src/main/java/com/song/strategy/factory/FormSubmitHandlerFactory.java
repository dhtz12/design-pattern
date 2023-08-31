package com.song.strategy.factory;

import com.song.strategy.strategy.FormSubmitHandler;
import lombok.NonNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 我们让 FormSubmitHandlerFactory 实现 InitializingBean 接口，
 * 在 afterPropertiesSet 方法中，基于 Spring 容器将所有 FormSubmitHandler 自动注册到 FORM_SUBMIT_HANDLER_MAP，
 * 从而 Spring 容器启动完成后， getHandler 方法可以直接通过 submitType 来获取对应的表单提交处理器。
 */

@Component
public class FormSubmitHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final
    Map<String, FormSubmitHandler<Serializable>> FORM_SUBMIT_HANDLER_MAP = new HashMap<>(8);

    private ApplicationContext appContext;

    /**
     * 根据提交类型获取对应的处理器
     *
     * @param submitType 提交类型
     * @return 提交类型对应的处理器
     */
    public FormSubmitHandler<Serializable> getHandler(String submitType) {
        return FORM_SUBMIT_HANDLER_MAP.get(submitType);
    }

    @Override
    public void afterPropertiesSet() {
        // 将 Spring 容器中所有的 FormSubmitHandler 注册到 FORM_SUBMIT_HANDLER_MAP
        appContext.getBeansOfType(FormSubmitHandler.class)
                  .values()
                  .forEach(handler -> FORM_SUBMIT_HANDLER_MAP.put(handler.getSubmitType(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}
