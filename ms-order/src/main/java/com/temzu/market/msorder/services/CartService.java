package com.temzu.market.msorder.services;

import com.temzu.market.msorder.dtos.CartDto;
import com.temzu.market.msorder.models.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

  Cart save(Cart cart);

  CartDto findById(Long id);

  Optional<Cart> findByUserId(Long userId);

  void addToCart(UUID cartId, Long productId);

  void clearCart(UUID cartId);

  UUID getCartForUser(Long userId, UUID cartUuid);
}
