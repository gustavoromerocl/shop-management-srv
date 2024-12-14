package com.duocuc.shop_management_srv.dto;

import java.util.Objects;

public class OrderItemDto {

  private Long productId;
  private String productName;
  private double price;
  private int quantity;

  // Getters y Setters
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  // Método equals para comparar objetos
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    OrderItemDto that = (OrderItemDto) o;
    return Double.compare(that.price, price) == 0 &&
        quantity == that.quantity &&
        Objects.equals(productId, that.productId) &&
        Objects.equals(productName, that.productName);
  }

  // Método hashCode
  @Override
  public int hashCode() {
    return Objects.hash(productId, productName, price, quantity);
  }

  // Método toString para representar el objeto como una cadena
  @Override
  public String toString() {
    return "OrderItemDto{" +
        "productId=" + productId +
        ", productName='" + productName + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
