package com.pieropan.getstore.controller;

import com.pieropan.getstore.request.LoginRequest;
import com.pieropan.getstore.request.UsuarioCadastroRequest;
import com.pieropan.getstore.response.UsuarioCadastradoResponse;
import com.pieropan.getstore.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioService usuarioService;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário inexistente ou senha inválida")
    })
    public ResponseEntity<String> realizarLogin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(usuarioService.realizarLogin(loginRequest));
    }

    @PostMapping
    public ResponseEntity<UsuarioCadastradoResponse> cadastrar(@RequestBody UsuarioCadastroRequest usuarioCadastroRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(usuarioCadastroRequest));
    }
}