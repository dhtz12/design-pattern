package com.song.factory_template_method.service;

import com.song.factory_template_method.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(value = "VipUserCart")
@Slf4j
public class VipUserCart extends AbstractCart {

    @Override
    protected void processCouponPrice(long userId, Item item) {
        log.info("processCouponPrice->VipUserCart");
        if (item.getQuantity() > 2) {
            item.setCouponPrice(item.getPrice()
                    .multiply(BigDecimal.valueOf(100 - 40).divide(new BigDecimal("100")))
                    .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
        } else {
            item.setCouponPrice(BigDecimal.ZERO);
        }
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }

    @Override
    public int getUserType() {
        return 3;
    }
}