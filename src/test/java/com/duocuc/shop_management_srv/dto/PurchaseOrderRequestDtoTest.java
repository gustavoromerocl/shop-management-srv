package com.duocuc.shop_management_srv.dto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PurchaseOrderRequestDtoTest {

  @Test
  void testGettersAndSetters() {
    // Crear datos de prueba
    OrderItemDto item1 = new OrderItemDto();
    item1.setProductId(1L);
    item1.setProductName("Product A");
    item1.setPrice(19.99);
    item1.setQuantity(3);

    OrderItemDto item2 = new OrderItemDto();
    item2.setProductId(2L);
    item2.setProductName("Product B");
    item2.setPrice(29.99);
    item2.setQuantity(2);

    List<OrderItemDto> products = List.of(item1, item2);

    // Crear y configurar la instancia de PurchaseOrderRequestDto
    PurchaseOrderRequestDto requestDto = new PurchaseOrderRequestDto();
    requestDto.setProducts(products);

    // Verificar los getters
    assertEquals(products, requestDto.getProducts());
    assertEquals(2, requestDto.getProducts().size());
    assertEquals("Product A", requestDto.getProducts().get(0).getProductName());
    assertEquals(19.99, requestDto.getProducts().get(0).getPrice());
  }
}
