package com.example.reciclagem.controllers;

import com.example.reciclagem.dtos.users.LoginDTO;
import com.example.reciclagem.dtos.users.TokenDTO;
import com.example.reciclagem.dtos.users.UsuarioCadastroDTO;
import com.example.reciclagem.dtos.users.UsuarioExibicaoDTO;
import com.example.reciclagem.models.UsuarioModel;
import com.example.reciclagem.security.service.TokenService;
import com.example.reciclagem.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO dto) {
        UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        Authentication auth = authenticationManager.authenticate(credentials);
        String token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO dto) {
        return usuarioService.salvarUsuario(dto);
    }
}
