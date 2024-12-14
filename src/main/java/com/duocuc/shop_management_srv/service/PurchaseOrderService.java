package com.duocuc.shop_management_srv.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.duocuc.shop_management_srv.dto.OrderItemDto;
import com.duocuc.shop_management_srv.dto.ProductDto;
import com.duocuc.shop_management_srv.dto.PurchaseOrderRequestDto;
import com.duocuc.shop_management_srv.model.OrderItem;
import com.duocuc.shop_management_srv.model.OrderStatus;
import com.duocuc.shop_management_srv.model.PurchaseOrder;
import com.duocuc.shop_management_srv.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final RestTemplate restTemplate;

  @Value("${product-service.url}")
  private String productServiceUrl;

  public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, RestTemplate restTemplate) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.restTemplate = restTemplate;
  }

  public PurchaseOrder createOrder(PurchaseOrderRequestDto requestDto) {
    // Extraer IDs de los productos
    List<Long> productIds = requestDto.getProducts()
        .stream()
        .map(OrderItemDto::getProductId)
        .collect(Collectors.toList());

    // Validar productos con el servicio de productos
    Map<Long, ProductDto> validProducts = validateProducts(productIds);

    // Crear y mapear los items de la orden
    List<OrderItem> validatedItems = requestDto.getProducts()
        .stream()
        .map(orderItem -> {
          ProductDto product = validProducts.get(orderItem.getProductId());
          if (product == null) {
            throw new IllegalArgumentException("El producto con ID " + orderItem.getProductId() + " no existe.");
          }
          return new OrderItem(
              product.getId(),
              product.getName(),
              product.getPrice(),
              orderItem.getQuantity());
        })
        .collect(Collectors.toList());

    // Calcular el total
    double totalAmount = validatedItems.stream()
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();

    // Crear la entidad PurchaseOrder
    PurchaseOrder order = new PurchaseOrder();
    order.setOrderNumber(generateOrderNumber()); // Genera el número de orden
    order.setOrderDate(LocalDateTime.now()); // Asigna la fecha actual
    order.setStatus(OrderStatus.PENDING); // Estado por defecto
    order.setProducts(validatedItems);
    order.setTotalAmount(totalAmount);

    // Guardar y retornar la orden
    return purchaseOrderRepository.save(order);
  }

  private Map<Long, ProductDto> validateProducts(List<Long> productIds) {
    String url = productServiceUrl + "/by-ids"; // Endpoint POST
    ProductDto[] products = restTemplate.postForObject(url, productIds, ProductDto[].class);
    if (products == null || products.length == 0) {
      throw new IllegalArgumentException("No se encontraron productos válidos para los IDs proporcionados.");
    }
    return List.of(products).stream()
        .collect(Collectors.toMap(ProductDto::getId, product -> product)); // Ahora usa el DTO de producto
  }

  String generateOrderNumber() {
    long count = purchaseOrderRepository.count(); // Total de órdenes existentes
    String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // Fecha en formato YYYYMMDD
    return "ORD-" + datePart + "-" + (count + 1); // Genera un número único basado en la fecha y el conteo
  }
}
