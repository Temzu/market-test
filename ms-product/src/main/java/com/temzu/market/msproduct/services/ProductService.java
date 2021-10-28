package com.temzu.market.msproduct.services;

import com.temzu.market.msproduct.dtos.ProductDto;
import com.temzu.market.msproduct.models.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface ProductService {

  Page<ProductDto> getAll(MultiValueMap<String, String> param, int page, int pageSize);

  Optional<Product> getById(Long id);

  ProductDto getProductDtoById(Long id);

  void deleteById(Long id);

  ProductDto save(ProductDto productDto);

  ProductDto update(ProductDto productDto);
}
