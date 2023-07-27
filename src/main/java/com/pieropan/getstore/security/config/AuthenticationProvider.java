package com.pieropan.getstore.security.config;

import com.pieropan.getstore.security.service.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class AuthenticationProvider {

    UsuarioServiceImpl usuarioService;

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = usuarioService.loadUserByUsername(email);
        if (Objects.isNull(userDetails)) return null;

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}