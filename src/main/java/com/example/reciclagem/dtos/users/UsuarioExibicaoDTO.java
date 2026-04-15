package com.example.reciclagem.dtos.users;

import com.example.reciclagem.models.UsuarioModel;

public record UsuarioExibicaoDTO(Long usuarioId,
                                 String nome,
                                 String email) {

    public UsuarioExibicaoDTO(UsuarioModel usuario) {
        this(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail());
    }
}