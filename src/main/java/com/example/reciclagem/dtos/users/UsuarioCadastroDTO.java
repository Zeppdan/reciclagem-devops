package com.example.reciclagem.dtos.users;

public record UsuarioCadastroDTO(  Long usuarioId,
                                   String nome,
                                   String email,
                                   String senha,
                                   String role) {
}
