package com.duocuc.shop_management_srv.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductDtoTest {

  @Test
  void testConstructorAndGetters() {
    Long id = 1L;
    String name = "Test Product";
    String description = "Test Description";
    Double price = 10.99;
    Integer stock = 50;
    String imageUrl = "http://example.com/image.jpg";
    CategoryDto category = CategoryDto.FRUTAS;

    ProductDto productDto = new ProductDto(id, name, description, price, stock, imageUrl, category);

    assertEquals(id, productDto.getId());
    assertEquals(name, productDto.getName());
    assertEquals(description, productDto.getDescription());
    assertEquals(price, productDto.getPrice());
    assertEquals(stock, productDto.getStock());
    assertEquals(imageUrl, productDto.getImageUrl());
    assertEquals(category, productDto.getCategory());
  }

  @Test
  void testSetters() {
    ProductDto productDto = new ProductDto();

    Long id = 2L;
    String name = "Updated Product";
    String description = "Updated Description";
    Double price = 20.50;
    Integer stock = 30;
    String imageUrl = "http://example.com/updated-image.jpg";
    CategoryDto category = CategoryDto.VERDURAS;

    productDto.setId(id);
    productDto.setName(name);
    productDto.setDescription(description);
    productDto.setPrice(price);
    productDto.setStock(stock);
    productDto.setImageUrl(imageUrl);
    productDto.setCategory(category);

    assertEquals(id, productDto.getId());
    assertEquals(name, productDto.getName());
    assertEquals(description, productDto.getDescription());
    assertEquals(price, productDto.getPrice());
    assertEquals(stock, productDto.getStock());
    assertEquals(imageUrl, productDto.getImageUrl());
    assertEquals(category, productDto.getCategory());
  }

  @Test
  void testEquality() {
    ProductDto productDto1 = new ProductDto(1L, "Product A", "Description A", 15.99, 10, "url1", CategoryDto.BEBIDAS);
    ProductDto productDto2 = new ProductDto(1L, "Product A", "Description A", 15.99, 10, "url1", CategoryDto.BEBIDAS);

    assertEquals(productDto1, productDto2);
  }

  @Test
  void testHashCode() {
    ProductDto productDto1 = new ProductDto(1L, "Product A", "Description A", 15.99, 10, "url1", CategoryDto.SNACKS);
    ProductDto productDto2 = new ProductDto(1L, "Product A", "Description A", 15.99, 10, "url1", CategoryDto.SNACKS);

    assertEquals(productDto1.hashCode(), productDto2.hashCode());
  }

  @Test
  void testToString() {
    ProductDto productDto = new ProductDto(1L, "Product B", "Description B", 25.00, 20, "url2", CategoryDto.CARNES);

    String expected = "ProductDto{id=1, name='Product B', description='Description B', price=25.0, stock=20, category=CARNES, imageUrl='url2'}";
    assertEquals(expected, productDto.toString());
  }
}
