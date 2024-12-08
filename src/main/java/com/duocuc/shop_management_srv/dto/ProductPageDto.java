package com.duocuc.shop_management_srv.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class ProductPageDto {

  private List<ProductDto> content;
  private int pageNumber;
  private int pageSize;
  private long totalElements;

  // Getters y setters
  public List<ProductDto> getContent() {
    return content;
  }

  public void setContent(List<ProductDto> content) {
    this.content = content;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  // Convertir a un objeto Page para Spring Data
  public Page<ProductDto> toPage() {
    return new PageImpl<>(content, PageRequest.of(pageNumber, pageSize), totalElements);
  }
}
