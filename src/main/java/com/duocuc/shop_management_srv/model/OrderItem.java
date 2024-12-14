package com.duocuc.shop_management_srv.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private String name;
  private Double price;
  private int quantity;

  // Constructor predeterminado
  public OrderItem() {
  }

  // Constructor con todos los campos necesarios
  public OrderItem(Long productId, String name, Double price, int quantity) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  // Getters y setters
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  // Sobrescribir equals
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    OrderItem orderItem = (OrderItem) o;
    return quantity == orderItem.quantity &&
        Objects.equals(productId, orderItem.productId) &&
        Objects.equals(name, orderItem.name) &&
        Objects.equals(price, orderItem.price);
  }

  // Sobrescribir hashCode
  @Override
  public int hashCode() {
    return Objects.hash(productId, name, price, quantity);
  }

  // Sobrescribir toString
  @Override
  public String toString() {
    return "OrderItem{" +
        "productId=" + productId +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
