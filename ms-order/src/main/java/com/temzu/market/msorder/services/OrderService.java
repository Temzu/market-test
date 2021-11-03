package com.temzu.market.msorder.services;

import com.temzu.market.msorder.dtos.CreateOrderDto;
import com.temzu.market.msorder.dtos.OrderDto;

public interface OrderService {

  OrderDto createFromCart(Long userId, CreateOrderDto createOrderDto);

}
