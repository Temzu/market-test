package com.temzu.market.msorder.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "product_id")
  private long productId;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price_per_product")
  private BigDecimal pricePerProduct;

  @Column(name = "price")
  private BigDecimal price;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public OrderItem(CartItem cartItem) {
    this.productId = cartItem.getProductId();
    this.quantity = cartItem.getQuantity();
    this.pricePerProduct = cartItem.getPricePerProduct();
    this.price = cartItem.getPrice();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    OrderItem orderItem = (OrderItem) o;
    return id != null && Objects.equals(id, orderItem.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
