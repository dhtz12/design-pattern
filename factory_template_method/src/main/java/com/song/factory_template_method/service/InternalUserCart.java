package com.song.factory_template_method.service;

import com.song.factory_template_method.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(value = "InternalUserCart")
@Slf4j
public class InternalUserCart extends AbstractCart {  
    @Override  
    protected void processCouponPrice(long userId, Item item) {
        log.info("processCouponPrice->InternalUserCart");
        item.setCouponPrice(BigDecimal.ZERO);
    }  
  
    @Override  
    protected void processDeliveryPrice(long userId, Item item) {  
        item.setDeliveryPrice(BigDecimal.ZERO);  
    }

    @Override
    public int getUserType() {
        return 1;
    }
}