package com.temzu.market.msproduct.services.impl;

import com.temzu.market.routinglib.dtos.ProductDto;
import com.temzu.market.msproduct.models.Product;
import com.temzu.market.msproduct.models.mappers.ProductMapper;
import com.temzu.market.msproduct.repositories.ProductRepository;
import com.temzu.market.msproduct.repositories.specifications.ProductSpecifications;
import com.temzu.market.msproduct.services.ProductService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper mapper;

  @Override
  public Page<ProductDto> getAll(MultiValueMap<String, String> param, int page, int pageSize) {
    return productRepository.findAll(ProductSpecifications.build(param),
        PageRequest.of(page - 1, pageSize)).map(
        mapper::toProductDto);
  }

  @Override
  public Optional<Product> getById(Long id) {
    return Optional.empty();
  }

  @Override
  public ProductDto getProductDtoById(Long id) {
    return null;
  }

  @Override
  public void deleteById(Long id) {

  }

  @Override
  public ProductDto save(ProductDto productDto) {
    return null;
  }

  @Override
  public ProductDto update(ProductDto productDto) {
    return null;
  }
}
