package com.temzu.market.msorder.repositories;

import com.temzu.market.msorder.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
