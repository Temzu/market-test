package com.temzu.market.msorder.controllers;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.routinglib.dtos.CreateOrderDto;
import com.temzu.market.routinglib.dtos.OrderDto;
import com.temzu.market.msorder.services.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  private final TokenService tokenService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto createOrderFromCart(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
      @RequestBody CreateOrderDto createOrderDto
  ) {
    return orderService.createFromCart(tokenService.getUserId(token), createOrderDto);
  }

  @GetMapping
  public List<OrderDto> getCurrentUserOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    return orderService.findAllOrdersByUserId(tokenService.getUserId(token));
  }

}
