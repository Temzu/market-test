package com.temzu.market.msorder.models;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "id")
  private UUID id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items;

  @Column(name = "price")
  private BigDecimal price;

  @Column
  private long userId;

  public void add(CartItem cartItem) {
    items.stream()
        .filter(ci -> ci.getProductId() == cartItem.getProductId())
        .findFirst()
        .ifPresentOrElse(
            ci -> ci.incrementQuantity(cartItem.getQuantity()),
            () -> {
              this.items.add(cartItem);
              cartItem.setCart(this);
            }
        );
    recalculate();
  }

  public void recalculate() {
    price = BigDecimal.ZERO;
    items.forEach(cartItem -> price = price.add(cartItem.getPrice()));
  }

  public void clear() {
    items.forEach(cartItem -> cartItem.setCart(null));
    items.clear();
    recalculate();
  }

  public CartItem getItemByProductId(Long productId) {
    return items.stream()
        .filter(cartItem -> cartItem.getProductId() == productId)
        .findFirst()
        .orElse(null);
  }

  public void merge(Cart another) {
    another.items.forEach(this::add);
  }

}
