package com.temzu.market.msproduct.models.mappers;

import com.temzu.market.msproduct.dtos.ProductDto;
import com.temzu.market.msproduct.models.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }

  public Product toProduct(ProductDto dto) {
    return mapper.map(dto, Product.class);
  }

}
