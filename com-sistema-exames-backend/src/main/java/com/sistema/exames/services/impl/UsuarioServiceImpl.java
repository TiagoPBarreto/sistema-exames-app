package com.sistema.exames.services.impl;

import com.sistema.exames.entity.Usuario;
import com.sistema.exames.entity.UsuarioRol;
import com.sistema.exames.excepciones.UsuarioFoundException;
import com.sistema.exames.repository.RolRepository;
import com.sistema.exames.repository.UsuarioRepository;
import com.sistema.exames.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioLocal != null){

            System.out.println("User already exists");
            throw new UsuarioFoundException("User already exists");

        }
        else {

            for (UsuarioRol usuarioRol: usuarioRoles){
                rolRepository.save(usuarioRol.getRol());

            }

            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);

        }

        return usuarioLocal;

    }

    @Override
    public Usuario obterUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void deletarUsuario(Long usuarioid) {
        usuarioRepository.deleteById(usuarioid);
    }
}

