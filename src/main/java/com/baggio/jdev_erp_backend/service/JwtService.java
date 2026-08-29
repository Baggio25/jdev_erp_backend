package com.baggio.jdev_erp_backend.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.baggio.jdev_erp_backend.exception.MsgApiException;
import com.baggio.jdev_erp_backend.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiraton}")
  private String expiraton;

  private SecretKey getKey() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public Claims extrairClaims(String token) {
    return Jwts.parser()
               .verifyWith(getKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
  }

  public String gerarToken(Usuario usuario) {
    return Jwts.builder()
                .subject(usuario.getLogin())
                .claim("usuarioId", usuario.getId())
                .claim("empresaId", usuario.getEmpresa().getId())
                .claim("login", usuario.getLogin())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiraton))
                .signWith(getKey())
                .compact();
  }

  public Long extrairEmpresaId() {
    Claims claims = extrairClaims(getToken());
    return claims.get("empresaId", Long.class);
  }
  
  public Long extrairEmpresaId(String token) {
    Claims claims = extrairClaims(token);
    return claims.get("empresaId", Long.class);
  }

  public Long extrairUsuarioId() {
    Claims claims = extrairClaims(getToken());
    return claims.get("usuarioId", Long.class);
  }
  
  public Long extrairUsuarioId(String token) {
    Claims claims = extrairClaims(token);
    return claims.get("usuarioId", Long.class);
  }
  
  public String extrairLogin(String token) {
    return extrairClaims(token).getSubject();
  }

  public Boolean validarToken(String token) {
    try {
      if(token == null || token.isEmpty()) {
        return false;
      }

      Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
      
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new MsgApiException("Token de acesso do usuário é inválido");
    }
  }

  private String getToken() {
    return (String) SecurityContextHolder
                      .getContext()
                      .getAuthentication()
                      .getCredentials();
  }

}
