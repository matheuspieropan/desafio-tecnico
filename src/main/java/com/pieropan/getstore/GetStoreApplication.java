package com.pieropan.getstore;

import com.pieropan.getstore.entity.Usuario;
import com.pieropan.getstore.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@SpringBootApplication
public class GetStoreApplication {

    UsuarioRepository repository;

    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(GetStoreApplication.class, args);
    }

    @Bean
    public void instanciarUsuario() {
        boolean semUsuarios = !repository.findAll().iterator().hasNext();
        if (semUsuarios) {
            repository.save(new Usuario("admin", "admin@admin.com", passwordEncoder.encode("123")));
        }
    }
}