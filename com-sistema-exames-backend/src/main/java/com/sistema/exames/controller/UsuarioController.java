package com.sistema.exames.controller;

import com.sistema.exames.entity.Rol;
import com.sistema.exames.entity.Usuario;
import com.sistema.exames.entity.UsuarioRol;
import com.sistema.exames.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder BCryptpasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setProfile("default.png");
        usuario.setPassword(this.BCryptpasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol>usuarioRoles = new HashSet<>();


        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setName("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }
    @GetMapping("/{username}")
    public Usuario obterUsuario(@PathVariable("username")String username){
        return usuarioService.obterUsuario(username);
    }
    @DeleteMapping("/{usuarioId}")
    public void deletarUsuario(@PathVariable("usuarioId")Long usuarioId){
        usuarioService.deletarUsuario(usuarioId);
    }
}

