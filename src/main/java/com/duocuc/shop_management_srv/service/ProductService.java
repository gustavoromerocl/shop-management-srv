package com.duocuc.shop_management_srv.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.duocuc.shop_management_srv.dto.ProductDto;
import com.duocuc.shop_management_srv.dto.ProductPageDto;

@Service
public class ProductService {

  private final RestTemplate restTemplate;

  @Value("${product-service.url}")
  private String productServiceUrl;

  public ProductService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ProductPageDto getAllProducts(int page, int size, String keyword) {
    StringBuilder urlBuilder = new StringBuilder(productServiceUrl)
        .append("?page=").append(page)
        .append("&size=").append(size);

    if (keyword != null && !keyword.trim().isEmpty()) {
      urlBuilder.append("&keyword=").append(keyword);
    }

    String url = urlBuilder.toString();

    ResponseEntity<ProductPageDto> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<>() {
        });

    return response.getBody();
  }

  public ProductDto getProductById(Long id) {
    String url = productServiceUrl + "/" + id;
    return restTemplate.getForObject(url, ProductDto.class);
  }
}
