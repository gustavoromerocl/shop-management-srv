package com.duocuc.shop_management_srv.service;

import com.duocuc.shop_management_srv.dto.ProductDto;
import com.duocuc.shop_management_srv.dto.ProductPageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

  @Mock
  private RestTemplate restTemplate;

  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    productService = new ProductService(restTemplate);
    productService.productServiceUrl = "http://localhost:8080/api/products";
  }

  @Test
  void testGetAllProducts() {
    ProductPageDto mockResponse = new ProductPageDto();
    ResponseEntity<ProductPageDto> responseEntity = ResponseEntity.ok(mockResponse);

    when(restTemplate.exchange(
        anyString(),
        eq(HttpMethod.GET),
        isNull(),
        any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

    ProductPageDto result = productService.getAllProducts(1, 10, "keyword");

    assertNotNull(result);
    assertEquals(mockResponse, result);
    verify(restTemplate, times(1)).exchange(
        contains("?page=1&size=10&keyword=keyword"),
        eq(HttpMethod.GET),
        isNull(),
        any(ParameterizedTypeReference.class));
  }

  @Test
  void testGetProductById() {
    Long productId = 1L;
    ProductDto mockProduct = new ProductDto(productId, "Product A", "Description A", 10.0, 5, "url", null);

    when(restTemplate.getForObject(anyString(), eq(ProductDto.class)))
        .thenReturn(mockProduct);

    ProductDto result = productService.getProductById(productId);

    assertNotNull(result);
    assertEquals(mockProduct, result);
    verify(restTemplate, times(1)).getForObject(contains("/" + productId), eq(ProductDto.class));
  }

  @Test
  void testGetProductsByIds() {
    List<Long> ids = Arrays.asList(1L, 2L, 3L);
    ProductDto[] mockProducts = {
        new ProductDto(1L, "Product A", "Description A", 10.0, 5, "url", null),
        new ProductDto(2L, "Product B", "Description B", 20.0, 3, "url", null)
    };

    when(restTemplate.getForObject(anyString(), eq(ProductDto[].class)))
        .thenReturn(mockProducts);

    List<ProductDto> result = productService.getProductsByIds(ids);

    assertNotNull(result);
    assertEquals(mockProducts.length, result.size());
    assertEquals(mockProducts[0].getId(), result.get(0).getId());
    verify(restTemplate, times(1)).getForObject(contains("/search/ids?ids="), eq(ProductDto[].class));
  }
}
