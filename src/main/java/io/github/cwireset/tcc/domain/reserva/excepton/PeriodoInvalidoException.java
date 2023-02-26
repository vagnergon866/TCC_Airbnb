package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoInvalidoException extends RuntimeException {

    public PeriodoInvalidoException(String descricao){
        super("Período inválido! " + descricao);
    }
}
