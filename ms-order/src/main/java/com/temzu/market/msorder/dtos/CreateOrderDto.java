package com.temzu.market.msorder.dtos;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDto {

  private UUID cartUuid;
  private String address;
}
