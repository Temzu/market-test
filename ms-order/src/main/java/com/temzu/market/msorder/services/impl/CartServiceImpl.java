package com.temzu.market.msorder.services.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msorder.models.Cart;
import com.temzu.market.msorder.models.CartItem;
import com.temzu.market.msorder.models.mappers.CartMapper;
import com.temzu.market.msorder.repositories.CartRepository;
import com.temzu.market.msorder.services.CartService;
import com.temzu.market.routinglib.clients.ProductClient;
import com.temzu.market.routinglib.dtos.CartDto;
import com.temzu.market.routinglib.dtos.ProductDto;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  private final ProductClient productClient;

  private final CartMapper cartMapper;

  @Override
  public Cart save(@NonNull Cart cart) {
    return cartRepository.save(cart);
  }

  @Override
  public Cart findById(UUID id) {
    return cartRepository.findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byName(id.toString(), Cart.class));
  }

  @Override
  public CartDto findDtoById(UUID id) {
    return cartMapper.toCartDto(findById(id));
  }

  @Override
  public Optional<Cart> findByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }

  @Override
  public void addToCart(UUID cartId, Long productId) {
    Cart cart = findById(cartId);
    Optional.ofNullable(cart.getItemByProductId(productId))
        .ifPresentOrElse(
            ci -> {
              ci.incrementQuantity();
              cart.recalculate();
            },
            () -> {
              ProductDto productDto = productClient.findProductById(productId);
              cart.add(new CartItem(productDto));
            }
        );
  }

  @Override
  public void clearCart(UUID cartId) {
    findById(cartId).clear();
  }

  @Override
  public UUID findCartForUser(Long userId, UUID cartUuid) {
    if (userId == null) {
      Cart cart = save(new Cart());
      return cart.getId();
    }

    if (cartUuid != null) {
      Cart cart = findById(cartUuid);
      findByUserId(userId).ifPresent(oldCart -> {
        cart.merge(oldCart);
        cartRepository.delete(oldCart);
      });
      cart.setUserId(userId);
    }

    Optional<Cart> cart = findByUserId(userId);
    if (cart.isPresent()) {
      return cart.get().getId();
    }
    Cart newCart = new Cart();
    newCart.setUserId(userId);
    save(newCart);
    return newCart.getId();
  }
}
