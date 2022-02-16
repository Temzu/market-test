package com.temzu.market.msorder.controllers;

import com.temzu.market.corelib.model.UserInfo;
import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.dtos.CartDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  private final TokenService tokenService;

  @PostMapping("/create")
  public UUID createNewCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
    if (token == null) {
      return cartService.findCartForUser(null, null);
    }
    return cartService.findCartForUser(tokenService.getUserId(token), null);
  }

  @GetMapping("/find/{uuid}")
  public CartDto getCurrentCart(@PathVariable UUID uuid) {
    return cartService.findDtoById(uuid);
  }

  @PostMapping("/add")
  public void addProductToCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
    cartService.addToCart(uuid, productId);
  }

  @PostMapping("/clear")
  public void clearCart(@RequestParam UUID uuid) {
    cartService.clearCart(uuid);
  }

}
