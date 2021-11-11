package com.temzu.market.msorder.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @OneToMany(mappedBy = "order")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private List<OrderItem> items;

  @Column(name = "address")
  private String address;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Order(Cart cart, Long userId, String address) {
    this.items = new ArrayList<>();
    this.userId = userId;
    this.address = address;
    this.price = cart.getPrice();
    cart.getItems().forEach(ci -> {
      OrderItem oi = new OrderItem(ci);
      oi.setOrder(this);
      this.items.add(oi);
    });
  }

}
