package com.duocuc.shop_management_srv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.duocuc.shop_management_srv.dto.PurchaseOrderRequestDto;
import com.duocuc.shop_management_srv.model.OrderItem;
import com.duocuc.shop_management_srv.model.PurchaseOrder;
import com.duocuc.shop_management_srv.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

  private final PurchaseOrderRepository purchaseOrderRepository;

  public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
  }

  public PurchaseOrder createOrder(PurchaseOrderRequestDto orderRequest) {
    List<OrderItem> items = orderRequest.getProducts().stream()
        .map(item -> {
          OrderItem orderItem = new OrderItem();
          orderItem.setProductId(item.getProductId());
          orderItem.setProductName(item.getProductName());
          orderItem.setPrice(item.getPrice());
          orderItem.setQuantity(item.getQuantity());
          return orderItem;
        }).collect(Collectors.toList());

    double totalAmount = items.stream()
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();

    PurchaseOrder order = new PurchaseOrder();
    order.setOrderNumber(orderRequest.getOrderNumber());
    order.setOrderDate(LocalDateTime.now());
    order.setTotalAmount(totalAmount);
    order.setStatus(orderRequest.getStatus());
    order.setItems(items);

    return purchaseOrderRepository.save(order);
  }
}
