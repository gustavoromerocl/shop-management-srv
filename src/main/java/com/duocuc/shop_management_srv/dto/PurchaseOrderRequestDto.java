package com.duocuc.shop_management_srv.dto;

import java.util.List;

import com.duocuc.shop_management_srv.model.OrderStatus;

public class PurchaseOrderRequestDto {

  private String orderNumber;
  private OrderStatus status;
  private List<OrderItemDto> products;

  // Getters y Setters
  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<OrderItemDto> getProducts() {
    return products;
  }

  public void setProducts(List<OrderItemDto> products) {
    this.products = products;
  }
}
