package com.example.reciclagem.services;

import com.example.reciclagem.dtos.users.UsuarioCadastroDTO;
import com.example.reciclagem.dtos.users.UsuarioExibicaoDTO;
import com.example.reciclagem.models.UsuarioModel;
import com.example.reciclagem.models.UsuarioRole;
import com.example.reciclagem.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        UserDetails existente = usuarioRepository.findByEmail(usuarioDTO.email());
        if (existente != null) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        UsuarioModel usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        if (usuarioDTO.role() == null) {
            usuario.setRole(UsuarioRole.USER);
        } else {
            try {
                usuario.setRole(UsuarioRole.valueOf(usuarioDTO.role().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Role inválida! Use USER ou ADMIN.");
            }
        }

        UsuarioModel salvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(salvo);
    }

    public List<UsuarioExibicaoDTO> listarTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioExibicaoDTO::new).toList();
    }

}
