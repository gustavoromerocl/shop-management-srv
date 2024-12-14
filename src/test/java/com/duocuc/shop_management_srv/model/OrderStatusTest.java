package com.duocuc.shop_management_srv.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class OrderStatusTest {

  @Test
  void testEnumValues() {
    // Obtener todos los valores del enum
    OrderStatus[] statuses = OrderStatus.values();

    // Verificar que el enum contiene los valores esperados
    assertEquals(3, statuses.length);
    assertArrayEquals(new OrderStatus[] {
        OrderStatus.PENDING,
        OrderStatus.COMPLETED,
        OrderStatus.CANCELED
    }, statuses);
  }

  @Test
  void testEnumValueOf() {
    // Verificar que valueOf devuelve el valor correcto
    assertEquals(OrderStatus.PENDING, OrderStatus.valueOf("PENDING"));
    assertEquals(OrderStatus.COMPLETED, OrderStatus.valueOf("COMPLETED"));
    assertEquals(OrderStatus.CANCELED, OrderStatus.valueOf("CANCELED"));
  }

  @Test
  void testInvalidValueOf() {
    // Verificar que valueOf lanza una excepción para valores inválidos
    assertThrows(IllegalArgumentException.class, () -> OrderStatus.valueOf("INVALID"));
  }
}
