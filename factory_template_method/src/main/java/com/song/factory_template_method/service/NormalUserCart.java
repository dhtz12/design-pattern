package com.song.factory_template_method.service;

import com.song.factory_template_method.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(value = "NormalUserCart")
@Slf4j
public class NormalUserCart extends AbstractCart {  
  
    @Override  
    protected void processCouponPrice(long userId, Item item) {
        log.info("processCouponPrice->NormalUserCart");
        item.setCouponPrice(BigDecimal.ZERO);
    }  
  
    @Override  
    protected void processDeliveryPrice(long userId, Item item) {  
        item.setDeliveryPrice(item.getPrice()  
                .multiply(BigDecimal.valueOf(item.getQuantity()))  
                .multiply(new BigDecimal("0.1")));  
    }

    @Override
    public int supportUserType() {
        return 2;
    }
}