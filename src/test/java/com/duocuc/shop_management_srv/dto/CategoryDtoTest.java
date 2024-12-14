package com.duocuc.shop_management_srv.dto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class CategoryDtoTest {

  @Test
  void testCategoryDtoValues() {
    // Obtener todos los valores de la enumeración
    CategoryDto[] categories = CategoryDto.values();

    // Verificar que los valores esperados estén presentes
    assertArrayEquals(
        new CategoryDto[] { CategoryDto.FRUTAS, CategoryDto.VERDURAS, CategoryDto.LACTEOS, CategoryDto.CARNES,
            CategoryDto.BEBIDAS, CategoryDto.SNACKS },
        categories);
  }

  @Test
  void testCategoryDtoValueOf() {
    // Verificar que cada valor se pueda obtener por su nombre
    assertEquals(CategoryDto.FRUTAS, CategoryDto.valueOf("FRUTAS"));
    assertEquals(CategoryDto.VERDURAS, CategoryDto.valueOf("VERDURAS"));
    assertEquals(CategoryDto.LACTEOS, CategoryDto.valueOf("LACTEOS"));
    assertEquals(CategoryDto.CARNES, CategoryDto.valueOf("CARNES"));
    assertEquals(CategoryDto.BEBIDAS, CategoryDto.valueOf("BEBIDAS"));
    assertEquals(CategoryDto.SNACKS, CategoryDto.valueOf("SNACKS"));
  }

  @Test
  void testInvalidValueOf() {
    // Verificar que un valor inválido lanza una excepción
    assertThrows(IllegalArgumentException.class, () -> CategoryDto.valueOf("INVALID"));
  }
}
