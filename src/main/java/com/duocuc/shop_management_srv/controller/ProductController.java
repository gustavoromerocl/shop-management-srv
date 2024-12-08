package com.duocuc.shop_management_srv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duocuc.shop_management_srv.dto.ProductDto;
import com.duocuc.shop_management_srv.dto.ProductPageDto;
import com.duocuc.shop_management_srv.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping()
  public ResponseEntity<ProductPageDto> getProducts(
      @RequestParam int page,
      @RequestParam int size,
      @RequestParam(required = false) String keyword) {
    ProductPageDto products = productService.getAllProducts(page, size, keyword);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ProductDto getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }
}
