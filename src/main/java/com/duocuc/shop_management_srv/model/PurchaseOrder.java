package com.duocuc.shop_management_srv.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class PurchaseOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String orderNumber;
  private LocalDateTime orderDate;
  private Double totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "purchase_order_id")
  private List<OrderItem> products;

  // Getters y setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<OrderItem> getProducts() {
    return products;
  }

  public void setProducts(List<OrderItem> products) {
    this.products = products;
  }

  // equals y hashCode
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    PurchaseOrder that = (PurchaseOrder) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(orderNumber, that.orderNumber) &&
        Objects.equals(orderDate, that.orderDate) &&
        Objects.equals(totalAmount, that.totalAmount) &&
        status == that.status &&
        Objects.equals(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderNumber, orderDate, totalAmount, status, products);
  }

  // toString
  @Override
  public String toString() {
    return "PurchaseOrder{" +
        "id=" + id +
        ", orderNumber='" + orderNumber + '\'' +
        ", orderDate=" + orderDate +
        ", totalAmount=" + totalAmount +
        ", status=" + status +
        ", products=" + products +
        '}';
  }

}
