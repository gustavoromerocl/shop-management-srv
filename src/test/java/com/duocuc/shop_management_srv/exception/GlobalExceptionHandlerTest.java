package com.duocuc.shop_management_srv.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

  @Test
  void handleIllegalArgumentException() {
    // Mock de la excepción y la solicitud web
    IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
    WebRequest webRequest = mock(WebRequest.class);
    when(webRequest.getDescription(false)).thenReturn("/api/test");

    // Ejecutar el manejador
    ResponseEntity<Object> response = globalExceptionHandler.handleIllegalArgumentException(exception, webRequest);

    // Validar respuesta
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    // Validar cuerpo de respuesta
    GlobalExceptionHandler.ErrorResponse errorResponse = (GlobalExceptionHandler.ErrorResponse) response.getBody();
    assertNotNull(errorResponse);
    assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatus());
    assertEquals("Invalid argument", errorResponse.getMessage());
    assertEquals("/api/test", errorResponse.getPath());
  }

  @Test
  void handleGeneralException() {
    // Mock de la excepción y la solicitud web
    Exception exception = new Exception("General error");
    WebRequest webRequest = mock(WebRequest.class);
    when(webRequest.getDescription(false)).thenReturn("/api/test");

    // Ejecutar el manejador
    ResponseEntity<Object> response = globalExceptionHandler.handleGeneralException(exception, webRequest);

    // Validar respuesta
    assertNotNull(response);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    // Validar cuerpo de respuesta
    GlobalExceptionHandler.ErrorResponse errorResponse = (GlobalExceptionHandler.ErrorResponse) response.getBody();
    assertNotNull(errorResponse);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatus());
    assertEquals("Ha ocurrido un error inesperado", errorResponse.getMessage());
    assertEquals("/api/test", errorResponse.getPath());
  }
}
