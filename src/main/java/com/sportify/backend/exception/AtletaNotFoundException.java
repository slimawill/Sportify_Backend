package com.sportify.backend.exception;

public class AtletaNotFoundException extends RuntimeException {

    public AtletaNotFoundException(String nomeUsuario) {
        super("Atleta não encontrado com o usuário: " + nomeUsuario);
    }

    public AtletaNotFoundException(String nomeUsuario, Throwable cause) {
        super("Atleta não encontrado com o usuário: " + nomeUsuario, cause);
    }
}
