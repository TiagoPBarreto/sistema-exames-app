package com.sistema.exames.controller;

import com.sistema.exames.config.JwtUtils;
import com.sistema.exames.entity.JwtRequest;
import com.sistema.exames.entity.JwtResponse;
import com.sistema.exames.entity.Usuario;
import com.sistema.exames.excepciones.UsuarioNotFoundException;
import com.sistema.exames.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")

    public ResponseEntity<?> gerarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }
        catch (UsuarioNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Usuario não encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void autenticar(String username,String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
        catch (DisabledException disabledException){
            throw  new Exception("Usuario desabilitado "+disabledException.getMessage());
        }
        catch (BadCredentialsException badCredentialsException){
            throw new Exception("Credenciais Invalidas "+ badCredentialsException.getMessage());
        }
    }
    @GetMapping("/atual-usuario")
    public Usuario obterUsuarioAtual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
