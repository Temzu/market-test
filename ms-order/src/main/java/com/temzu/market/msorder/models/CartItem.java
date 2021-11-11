package com.temzu.market.msorder.models;

import com.temzu.market.routinglib.dtos.ProductDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

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

  public CartItem(ProductDto product) {
    this.productId = product.getId();
    this.quantity = 1;
    this.pricePerProduct = product.getPrice();
    this.price = this.pricePerProduct;
  }

  public void incrementQuantity() {
    quantity++;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

  public void incrementQuantity(int amount) {
    quantity += amount;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

  public void decrementQuantity() {
    quantity--;
    price = pricePerProduct.multiply(new BigDecimal(quantity));
  }

}
