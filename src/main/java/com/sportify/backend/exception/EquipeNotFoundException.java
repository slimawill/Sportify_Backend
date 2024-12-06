package com.sportify.backend.exception;

public class EquipeNotFoundException extends ResourceNotFoundException {
    public EquipeNotFoundException(String message) {
        super(message);
    }

    public EquipeNotFoundException(Long id) {
        super("Equipe", "id", id);
    }
} 