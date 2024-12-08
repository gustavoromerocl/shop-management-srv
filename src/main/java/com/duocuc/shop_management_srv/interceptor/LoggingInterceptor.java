package com.duocuc.shop_management_srv.interceptor;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    logger.info("Incoming Request: {} {}", request.getMethod(), request.getRequestURI());
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String paramName = parameterNames.nextElement();
      logger.info("Parameter: {} = {}", paramName, request.getParameter(paramName));
    }
    return true; // Continúa con la ejecución de la request
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    logger.info("Response Status: {}", response.getStatus());
    if (ex != null) {
      logger.error("Request Exception: {}", ex.getMessage());
    }
  }
}
