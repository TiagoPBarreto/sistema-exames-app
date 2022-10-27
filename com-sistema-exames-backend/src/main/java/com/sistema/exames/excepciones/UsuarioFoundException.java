package com.sistema.exames.excepciones;

public class UsuarioFoundException extends Exception{
    public  UsuarioFoundException(){
        super("Usuario ja existe na base de dados,tente novamente com outro usuario");
    }
    public UsuarioFoundException(String menssage){
    super(menssage);
    }
}
