package com.duocuc.shop_management_srv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class PurchaseOrderTest {

  @Test
  void testConstructorAndGetters() {
    // Datos de prueba
    Long id = 1L;
    String orderNumber = "ORD123";
    LocalDateTime orderDate = LocalDateTime.now();
    Double totalAmount = 100.0;
    OrderStatus status = OrderStatus.PENDING;

    List<OrderItem> products = new ArrayList<>();
    products.add(new OrderItem(1L, "Product A", 10.0, 2));
    products.add(new OrderItem(2L, "Product B", 20.0, 3));

    // Crear objeto de prueba
    PurchaseOrder order = new PurchaseOrder();
    order.setId(id);
    order.setOrderNumber(orderNumber);
    order.setOrderDate(orderDate);
    order.setTotalAmount(totalAmount);
    order.setStatus(status);
    order.setProducts(products);

    // Verificaciones
    assertEquals(id, order.getId());
    assertEquals(orderNumber, order.getOrderNumber());
    assertEquals(orderDate, order.getOrderDate());
    assertEquals(totalAmount, order.getTotalAmount());
    assertEquals(status, order.getStatus());
    assertEquals(products, order.getProducts());
  }

  @Test
  void testSetters() {
    // Crear objeto vacío
    PurchaseOrder order = new PurchaseOrder();

    // Datos de prueba
    Long id = 2L;
    String orderNumber = "ORD456";
    LocalDateTime orderDate = LocalDateTime.now().minusDays(1);
    Double totalAmount = 250.0;
    OrderStatus status = OrderStatus.COMPLETED;

    List<OrderItem> products = new ArrayList<>();
    products.add(new OrderItem(3L, "Product C", 15.0, 1));
    products.add(new OrderItem(4L, "Product D", 30.0, 5));

    // Configurar valores
    order.setId(id);
    order.setOrderNumber(orderNumber);
    order.setOrderDate(orderDate);
    order.setTotalAmount(totalAmount);
    order.setStatus(status);
    order.setProducts(products);

    // Verificaciones
    assertEquals(id, order.getId());
    assertEquals(orderNumber, order.getOrderNumber());
    assertEquals(orderDate, order.getOrderDate());
    assertEquals(totalAmount, order.getTotalAmount());
    assertEquals(status, order.getStatus());
    assertEquals(products, order.getProducts());
  }

  @Test
  void testEquality() {
    // Crear dos objetos iguales
    PurchaseOrder order1 = new PurchaseOrder();
    order1.setId(1L);
    order1.setOrderNumber("ORD789");

    PurchaseOrder order2 = new PurchaseOrder();
    order2.setId(1L);
    order2.setOrderNumber("ORD789");

    // Verificar igualdad
    assertEquals(order1, order2);
    assertEquals(order1.hashCode(), order2.hashCode());
  }

  @Test
  void testHashCode() {
    // Crear dos objetos diferentes
    PurchaseOrder order1 = new PurchaseOrder();
    order1.setId(1L);
    order1.setOrderNumber("ORD789");

    PurchaseOrder order2 = new PurchaseOrder();
    order2.setId(2L);
    order2.setOrderNumber("ORD456");

    // Verificar que los hashCodes son diferentes
    assertNotEquals(order1.hashCode(), order2.hashCode());
  }

  @Test
  void testToString() {
    // Datos de prueba
    PurchaseOrder order = new PurchaseOrder();
    order.setId(1L);
    order.setOrderNumber("ORD123");
    order.setTotalAmount(200.0);
    order.setStatus(OrderStatus.PENDING);

    // Verificar toString
    String expected = "PurchaseOrder{id=1, orderNumber='ORD123', orderDate=null, totalAmount=200.0, status=PENDING, products=null}";
    assertEquals(expected, order.toString());
  }
}
