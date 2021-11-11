package com.temzu.market.msorder.repositories;

import com.temzu.market.msorder.models.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findAllByUserId(Long userId);
}
