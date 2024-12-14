package com.duocuc.shop_management_srv.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

import com.duocuc.shop_management_srv.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class JwtAuthenticationFilterTest {

  @Mock
  private JwtUtils jwtUtils;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
    SecurityContextHolder.clearContext(); // Limpia el contexto antes de cada prueba
  }

  @Test
  void testDoFilterInternalWithValidToken() throws Exception {
    // Mock del token
    String token = "validToken";
    Claims claims = mock(Claims.class);
    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtUtils.validateToken(token)).thenReturn(true);
    when(jwtUtils.getClaimsFromToken(token)).thenReturn(claims);
    when(claims.getSubject()).thenReturn("testuser");
    when(claims.get("roles", String.class)).thenReturn("ROLE_USER,ROLE_ADMIN");

    // Ejecutar el filtro
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verificaciones
    verify(jwtUtils).validateToken(token);
    verify(jwtUtils).getClaimsFromToken(token);
    verify(filterChain).doFilter(request, response);
    assertNotNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void testDoFilterInternalWithInvalidToken() throws Exception {
    // Mock de un token inválido
    String token = "invalidToken";
    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtUtils.validateToken(token)).thenReturn(false);

    // Ejecutar el filtro
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verificaciones
    verify(jwtUtils).validateToken(token);
    verify(jwtUtils, never()).getClaimsFromToken(anyString());
    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void testDoFilterInternalWithoutToken() throws Exception {
    // Limpiar el contexto de seguridad antes de la prueba
    SecurityContextHolder.clearContext();

    // Mock de la ausencia de token
    when(request.getHeader("Authorization")).thenReturn(null);

    // Ejecutar el filtro
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verificar que no se haya configurado ningún token de autenticación en el
    // contexto
    assertNull(SecurityContextHolder.getContext().getAuthentication());

    // Verificar que el filtro continúe con la cadena
    verify(filterChain).doFilter(request, response);
  }

}
