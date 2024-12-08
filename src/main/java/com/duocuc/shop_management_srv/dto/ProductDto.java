package com.duocuc.shop_management_srv.dto;

public class ProductDto {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private Integer stock;
  private CategoryDto category; // Usa la misma enumeración que en el modelo
  private String imageUrl;

  public ProductDto() {
  }

  public ProductDto(Long id, String name, String description, Double price, Integer stock, String imageUrl,
      CategoryDto category) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.category = category;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public CategoryDto getCategory() {
    return category;
  }

  public void setCategory(CategoryDto category) {
    this.category = category;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
