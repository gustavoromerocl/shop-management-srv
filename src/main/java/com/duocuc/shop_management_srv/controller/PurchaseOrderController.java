package com.duocuc.shop_management_srv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duocuc.shop_management_srv.dto.PurchaseOrderRequestDto;
import com.duocuc.shop_management_srv.model.PurchaseOrder;
import com.duocuc.shop_management_srv.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {

  private final PurchaseOrderService purchaseOrderService;

  public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
    this.purchaseOrderService = purchaseOrderService;
  }

  @PostMapping
  public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrderRequestDto orderRequest) {
    PurchaseOrder order = purchaseOrderService.createOrder(orderRequest);
    return ResponseEntity.ok(order);
  }
}
