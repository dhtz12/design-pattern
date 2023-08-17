package com.song.factory_template_method;

import com.song.factory_template_method.service.AbstractCart;
import com.song.factory_template_method.service.IUserCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class FactoryTemplateMethodApplicationTests {

    @Autowired
    private List<IUserCart> userCartList;


    @Test
    public void addCart() {

        int userType = 1;
        long userId = 1;
        Map<Long, Integer> itemMap = new HashMap<>();
        itemMap.put(1L, 2);


        Optional.ofNullable(userCartList)
                .map(cartList -> cartList.stream()
                        .filter(cart -> cart.getUserType() == userType)
                        .collect(Collectors.toList())).orElseThrow(() -> new IllegalArgumentException("")).forEach(cart -> cart.process(userId, itemMap));

    }

}
