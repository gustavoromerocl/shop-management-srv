package com.duocuc.shop_management_srv.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class OrderItemDtoTest {

  @Test
  void testConstructorAndGetters() {
    // Datos de prueba
    Long productId = 1L;
    String productName = "Product A";
    double price = 19.99;
    int quantity = 3;

    // Creación del objeto
    OrderItemDto orderItemDto = new OrderItemDto();
    orderItemDto.setProductId(productId);
    orderItemDto.setProductName(productName);
    orderItemDto.setPrice(price);
    orderItemDto.setQuantity(quantity);

    // Verificaciones
    assertEquals(productId, orderItemDto.getProductId());
    assertEquals(productName, orderItemDto.getProductName());
    assertEquals(price, orderItemDto.getPrice());
    assertEquals(quantity, orderItemDto.getQuantity());
  }

  @Test
  void testSetters() {
    // Creación del objeto
    OrderItemDto orderItemDto = new OrderItemDto();

    // Datos de prueba
    Long productId = 2L;
    String productName = "Product B";
    double price = 29.99;
    int quantity = 5;

    // Uso de setters
    orderItemDto.setProductId(productId);
    orderItemDto.setProductName(productName);
    orderItemDto.setPrice(price);
    orderItemDto.setQuantity(quantity);

    // Verificaciones
    assertEquals(productId, orderItemDto.getProductId());
    assertEquals(productName, orderItemDto.getProductName());
    assertEquals(price, orderItemDto.getPrice());
    assertEquals(quantity, orderItemDto.getQuantity());
  }

  @Test
  void testEquality() {
    // Creación de dos objetos iguales
    OrderItemDto orderItemDto1 = new OrderItemDto();
    orderItemDto1.setProductId(1L);
    orderItemDto1.setProductName("Product A");
    orderItemDto1.setPrice(19.99);
    orderItemDto1.setQuantity(3);

    OrderItemDto orderItemDto2 = new OrderItemDto();
    orderItemDto2.setProductId(1L);
    orderItemDto2.setProductName("Product A");
    orderItemDto2.setPrice(19.99);
    orderItemDto2.setQuantity(3);

    // Verificación de igualdad
    assertEquals(orderItemDto1, orderItemDto2);
  }

  @Test
  void testHashCode() {
    // Creación de dos objetos iguales
    OrderItemDto orderItemDto1 = new OrderItemDto();
    orderItemDto1.setProductId(1L);
    orderItemDto1.setProductName("Product A");
    orderItemDto1.setPrice(19.99);
    orderItemDto1.setQuantity(3);

    OrderItemDto orderItemDto2 = new OrderItemDto();
    orderItemDto2.setProductId(1L);
    orderItemDto2.setProductName("Product A");
    orderItemDto2.setPrice(19.99);
    orderItemDto2.setQuantity(3);

    // Verificación de hashCode
    assertEquals(orderItemDto1.hashCode(), orderItemDto2.hashCode());
  }

  @Test
  void testToString() {
    // Datos de prueba
    Long productId = 1L;
    String productName = "Product A";
    double price = 19.99;
    int quantity = 3;

    // Creación del objeto
    OrderItemDto orderItemDto = new OrderItemDto();
    orderItemDto.setProductId(productId);
    orderItemDto.setProductName(productName);
    orderItemDto.setPrice(price);
    orderItemDto.setQuantity(quantity);

    // Verificación de toString
    String expected = "OrderItemDto{productId=1, productName='Product A', price=19.99, quantity=3}";
    assertEquals(expected, orderItemDto.toString());
  }
}
