package com.duocuc.shop_management_srv.dto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

class ProductPageDtoTest {

  @Test
  void testGettersAndSetters() {
    // Crear datos de prueba
    ProductDto product1 = new ProductDto(1L, "Product A", "Description A", 10.0, 5, "url1", CategoryDto.FRUTAS);
    ProductDto product2 = new ProductDto(2L, "Product B", "Description B", 15.0, 10, "url2", CategoryDto.VERDURAS);
    List<ProductDto> content = List.of(product1, product2);
    int pageNumber = 1;
    int pageSize = 2;
    long totalElements = 100;

    // Crear y configurar la instancia de ProductPageDto
    ProductPageDto productPageDto = new ProductPageDto();
    productPageDto.setContent(content);
    productPageDto.setPageNumber(pageNumber);
    productPageDto.setPageSize(pageSize);
    productPageDto.setTotalElements(totalElements);

    // Verificar los getters
    assertEquals(content, productPageDto.getContent());
    assertEquals(pageNumber, productPageDto.getPageNumber());
    assertEquals(pageSize, productPageDto.getPageSize());
    assertEquals(totalElements, productPageDto.getTotalElements());
  }

  @Test
  void testToPage() {
    // Crear datos de prueba
    ProductDto product1 = new ProductDto(1L, "Product A", "Description A", 10.0, 5, "url1", CategoryDto.FRUTAS);
    ProductDto product2 = new ProductDto(2L, "Product B", "Description B", 15.0, 10, "url2", CategoryDto.VERDURAS);
    List<ProductDto> content = List.of(product1, product2);
    int pageNumber = 1;
    int pageSize = 2;
    long totalElements = 100;

    // Crear y configurar la instancia de ProductPageDto
    ProductPageDto productPageDto = new ProductPageDto();
    productPageDto.setContent(content);
    productPageDto.setPageNumber(pageNumber);
    productPageDto.setPageSize(pageSize);
    productPageDto.setTotalElements(totalElements);

    // Convertir a un objeto Page
    Page<ProductDto> page = productPageDto.toPage();

    // Verificar la conversión
    assertNotNull(page);
    assertEquals(content, page.getContent());
    assertEquals(pageNumber, page.getNumber());
    assertEquals(pageSize, page.getSize());
    assertEquals(totalElements, page.getTotalElements());
  }
}
