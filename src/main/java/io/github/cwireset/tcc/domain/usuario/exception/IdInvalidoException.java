package io.github.cwireset.tcc.domain.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdInvalidoException extends RuntimeException {

    public IdInvalidoException(Long id){
        super("Nenhum(a) Usuario com Id com o valor '" + id + "' foi encontrado.");
    }
}
