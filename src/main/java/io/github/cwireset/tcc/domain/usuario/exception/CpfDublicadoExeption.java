package io.github.cwireset.tcc.domain.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfDublicadoExeption extends RuntimeException {

    public CpfDublicadoExeption(String cpf){
        super("Já existe um recurso do tipo Usuario com CPF com o valor '" + cpf + "'.");
    }
}
