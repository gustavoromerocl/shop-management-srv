package com.duocuc.shop_management_srv.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class OrderItemTest {

  @Test
  void testConstructorAndGetters() {
    Long productId = 1L;
    String name = "Test Product";
    Double price = 19.99;
    int quantity = 3;

    OrderItem orderItem = new OrderItem(productId, name, price, quantity);

    assertEquals(productId, orderItem.getProductId());
    assertEquals(name, orderItem.getName());
    assertEquals(price, orderItem.getPrice());
    assertEquals(quantity, orderItem.getQuantity());
  }

  @Test
  void testSetters() {
    OrderItem orderItem = new OrderItem();

    Long productId = 2L;
    String name = "Updated Product";
    Double price = 29.99;
    int quantity = 5;

    orderItem.setProductId(productId);
    orderItem.setName(name);
    orderItem.setPrice(price);
    orderItem.setQuantity(quantity);

    assertEquals(productId, orderItem.getProductId());
    assertEquals(name, orderItem.getName());
    assertEquals(price, orderItem.getPrice());
    assertEquals(quantity, orderItem.getQuantity());
  }

  @Test
  void testEquality() {
    OrderItem orderItem1 = new OrderItem(1L, "Product A", 19.99, 3);
    OrderItem orderItem2 = new OrderItem(1L, "Product A", 19.99, 3);

    assertEquals(orderItem1, orderItem2);
  }

  @Test
  void testHashCode() {
    OrderItem orderItem1 = new OrderItem(1L, "Product A", 19.99, 3);
    OrderItem orderItem2 = new OrderItem(1L, "Product A", 19.99, 3);

    assertEquals(orderItem1.hashCode(), orderItem2.hashCode());
  }

  @Test
  void testToString() {
    OrderItem orderItem = new OrderItem(1L, "Product B", 25.00, 2);

    String expected = "OrderItem{productId=1, name='Product B', price=25.0, quantity=2}";
    assertEquals(expected, orderItem.toString());
  }
}
