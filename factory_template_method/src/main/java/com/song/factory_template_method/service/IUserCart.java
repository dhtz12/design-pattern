package com.song.factory_template_method.service;

import com.song.factory_template_method.entity.Cart;

import java.util.Map;

public interface IUserCart {

    public Cart process(long userId, Map<Long, Integer> items);

    public int getUserType();
}
