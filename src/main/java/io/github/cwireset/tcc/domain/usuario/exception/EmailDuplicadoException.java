package io.github.cwireset.tcc.domain.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email){
        super("Já existe um recurso do tipo Usuario com E-Mail com o valor '"+ email +"'.");
    }
}
