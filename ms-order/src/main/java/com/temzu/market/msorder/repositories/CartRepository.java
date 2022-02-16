package com.temzu.market.msorder.repositories;

import com.temzu.market.msorder.models.Cart;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, UUID> {

  Optional<Cart> findByUserId(Long userId);
}
