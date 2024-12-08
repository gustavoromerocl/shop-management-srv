package com.duocuc.shop_management_srv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duocuc.shop_management_srv.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
