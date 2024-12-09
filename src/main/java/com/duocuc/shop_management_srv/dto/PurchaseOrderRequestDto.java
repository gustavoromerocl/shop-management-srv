package com.duocuc.shop_management_srv.dto;

import java.util.List;

public class PurchaseOrderRequestDto {

  private List<OrderItemDto> products;

  // Getters y Setters
  public List<OrderItemDto> getProducts() {
    return products;
  }

  public void setProducts(List<OrderItemDto> products) {
    this.products = products;
  }
}
