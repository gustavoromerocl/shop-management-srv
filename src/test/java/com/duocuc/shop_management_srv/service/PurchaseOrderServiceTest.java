package com.duocuc.shop_management_srv.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.duocuc.shop_management_srv.dto.OrderItemDto;
import com.duocuc.shop_management_srv.dto.ProductDto;
import com.duocuc.shop_management_srv.dto.PurchaseOrderRequestDto;
import com.duocuc.shop_management_srv.model.OrderStatus;
import com.duocuc.shop_management_srv.model.PurchaseOrder;
import com.duocuc.shop_management_srv.repository.PurchaseOrderRepository;

class PurchaseOrderServiceTest {

  @Mock
  private PurchaseOrderRepository purchaseOrderRepository;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private PurchaseOrderService purchaseOrderService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateOrderSuccess() {
    // Datos de prueba
    PurchaseOrderRequestDto requestDto = new PurchaseOrderRequestDto();
    OrderItemDto orderItemDto1 = new OrderItemDto();
    orderItemDto1.setProductId(1L);
    orderItemDto1.setQuantity(2);
    OrderItemDto orderItemDto2 = new OrderItemDto();
    orderItemDto2.setProductId(2L);
    orderItemDto2.setQuantity(1);
    requestDto.setProducts(List.of(orderItemDto1, orderItemDto2));

    ProductDto productDto1 = new ProductDto(1L, "Product A", "Description A", 10.0, 50, "urlA", null);
    ProductDto productDto2 = new ProductDto(2L, "Product B", "Description B", 20.0, 30, "urlB", null);

    when(restTemplate.postForObject(anyString(), any(), eq(ProductDto[].class)))
        .thenReturn(new ProductDto[] { productDto1, productDto2 });

    when(purchaseOrderRepository.count()).thenReturn(10L);
    when(purchaseOrderRepository.save(any(PurchaseOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // Ejecutar el método
    PurchaseOrder createdOrder = purchaseOrderService.createOrder(requestDto);

    // Verificaciones
    assertNotNull(createdOrder);
    assertEquals("ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-11",
        createdOrder.getOrderNumber());
    assertEquals(OrderStatus.PENDING, createdOrder.getStatus());
    assertEquals(2, createdOrder.getProducts().size());
    assertEquals(40.0, createdOrder.getTotalAmount()); // 10 * 2 + 20 * 1

    verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(ProductDto[].class));
    verify(purchaseOrderRepository, times(1)).save(any(PurchaseOrder.class));
  }

  @Test
  void testCreateOrderProductNotFound() {
    // Datos de prueba
    PurchaseOrderRequestDto requestDto = new PurchaseOrderRequestDto();
    OrderItemDto orderItemDto = new OrderItemDto();
    orderItemDto.setProductId(1L);
    orderItemDto.setQuantity(2);
    requestDto.setProducts(List.of(orderItemDto));

    when(restTemplate.postForObject(anyString(), any(), eq(ProductDto[].class))).thenReturn(new ProductDto[] {});

    // Ejecutar y verificar excepción
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> purchaseOrderService.createOrder(requestDto));

    assertEquals("No se encontraron productos válidos para los IDs proporcionados.", exception.getMessage());
    verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(ProductDto[].class));
    verify(purchaseOrderRepository, never()).save(any(PurchaseOrder.class));
  }

  @Test
  void testCreateOrderInvalidProduct() {
    // Datos de prueba
    PurchaseOrderRequestDto requestDto = new PurchaseOrderRequestDto();
    OrderItemDto orderItemDto = new OrderItemDto();
    orderItemDto.setProductId(1L);
    orderItemDto.setQuantity(2);
    requestDto.setProducts(List.of(orderItemDto));

    ProductDto productDto = new ProductDto(2L, "Product B", "Description B", 20.0, 30, "urlB", null);

    when(restTemplate.postForObject(anyString(), any(), eq(ProductDto[].class)))
        .thenReturn(new ProductDto[] { productDto });

    // Ejecutar y verificar excepción
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> purchaseOrderService.createOrder(requestDto));

    assertEquals("El producto con ID 1 no existe.", exception.getMessage());
    verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(ProductDto[].class));
    verify(purchaseOrderRepository, never()).save(any(PurchaseOrder.class));
  }

  @Test
  void testGenerateOrderNumber() {
    when(purchaseOrderRepository.count()).thenReturn(5L);

    String orderNumber = purchaseOrderService.generateOrderNumber();

    assertEquals("ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-6", orderNumber);
  }
}
