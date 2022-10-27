package com.sistema.exames;

import com.sistema.exames.entity.Rol;
import com.sistema.exames.entity.Usuario;
import com.sistema.exames.entity.UsuarioRol;
import com.sistema.exames.excepciones.UsuarioFoundException;
import com.sistema.exames.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ComSistemaExamesBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ComSistemaExamesBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

			/*try {
				Usuario usuario = new Usuario();
				usuario.setName("Alex");
				usuario.setApellido("Soto");
				usuario.setUsername("alex");
				usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
				usuario.setEmail("alex@gmail.com");
				usuario.setPhone("988212020");
				usuario.setProfile("foto.png");
				Rol rol = new Rol();
				rol.setRolId(1L);
				rol.setName("ADMIN");
				Set<UsuarioRol> usuariosRoles = new HashSet<>();
				UsuarioRol usuarioRol = new UsuarioRol();
				usuarioRol.setRol(rol);
				usuarioRol.setUsuario(usuario);
				usuariosRoles.add(usuarioRol);
				Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario,usuariosRoles);
				System.out.println(usuarioGuardado.getUsername());
			}
			catch (UsuarioFoundException exception){
				exception.printStackTrace();
			}*/
	}
}
