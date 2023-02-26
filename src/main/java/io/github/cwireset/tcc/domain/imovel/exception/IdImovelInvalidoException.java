package io.github.cwireset.tcc.domain.imovel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdImovelInvalidoException extends RuntimeException {

    public IdImovelInvalidoException(Long idImovel){
        super("Nenhum(a) Imovel com Id com o valor '" + idImovel + "' foi encontrado.");
    }
}
