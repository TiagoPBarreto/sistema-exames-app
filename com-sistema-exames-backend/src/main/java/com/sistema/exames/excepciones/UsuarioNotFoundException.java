package com.sistema.exames.excepciones;

public class UsuarioNotFoundException extends Exception {


    public  UsuarioNotFoundException(){
        super("Usuario não na base de dados,tente novamente");
    }
    public UsuarioNotFoundException(String menssage){
        super(menssage);
    }
}
