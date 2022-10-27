package com.sistema.exames.services;

import com.sistema.exames.entity.Usuario;
import com.sistema.exames.entity.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol>usuarioRoles)throws Exception;

    public Usuario obterUsuario(String username);

    public void deletarUsuario(Long usuarioid);
}
