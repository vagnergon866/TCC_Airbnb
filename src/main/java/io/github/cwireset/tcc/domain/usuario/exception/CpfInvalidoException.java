package io.github.cwireset.tcc.domain.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String cpf){
        super("Nenhum(a) Usuario com CPF com o valor '" + cpf + "' foi encontrado.");
    }
}
