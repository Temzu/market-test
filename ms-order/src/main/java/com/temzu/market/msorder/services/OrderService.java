package com.temzu.market.msorder.services;

import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import java.util.List;

public interface OrderService {

  OrderDto createFromCart(Long userId, CreateOrderDto createOrderDto);

  List<OrderDto> findAllOrdersByUserId(Long userId);

}
