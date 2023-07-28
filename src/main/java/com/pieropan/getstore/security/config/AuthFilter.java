package com.pieropan.getstore.security.config;

import com.pieropan.getstore.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {

    AuthenticationProvider authenticationProvider;

    TokenService tokenService;

    @SneakyThrows({ServletException.class, IOException.class})
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) {
        String authorization = request.getHeader("Authorization");
        String token = Objects.nonNull(authorization) ? authorization.replace("Bearer ", "") : null;

        if (Objects.nonNull(token) && tokenValidado(token)) {
            String email = tokenService.getClaims(token).get("email").toString();
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.getAuthentication(email));
        }

        filter.doFilter(request, response);
    }

    boolean tokenValidado(String token) {
        return tokenService.tokenValidado(token);
    }
}