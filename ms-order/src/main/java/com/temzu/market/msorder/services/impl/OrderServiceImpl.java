package com.temzu.market.msorder.services.impl;

import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import com.temzu.market.msorder.models.Cart;
import com.temzu.market.msorder.models.Order;
import com.temzu.market.msorder.models.mappers.OrderMapper;
import com.temzu.market.msorder.repositories.OrderRepository;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.msorder.services.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final CartService cartService;

  private final OrderMapper orderMapper;

  @Override
  public OrderDto createFromCart(Long userId, CreateOrderDto createOrderDto) {
    Cart cart = cartService.findById(createOrderDto.getCartUuid());
    Order order = new Order(cart, userId, createOrderDto.getAddress());
    return orderMapper.toOrderDto(orderRepository.save(order));
  }

  @Override
  public List<OrderDto> findAllOrdersByUserId(Long userId) {
    return orderRepository.findAllByUserId(userId).stream()
        .map(orderMapper::toOrderDto)
        .collect(Collectors.toList());
  }
}
