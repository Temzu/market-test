package com.temzu.market.msorder.repositories;

import com.temzu.market.msorder.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
