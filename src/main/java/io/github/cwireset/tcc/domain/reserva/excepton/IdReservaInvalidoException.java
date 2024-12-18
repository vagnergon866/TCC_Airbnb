package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdReservaInvalidoException extends RuntimeException {

    public IdReservaInvalidoException(Long idReserva){
        super("Nenhum(a) Reserva com Id com o valor '" + idReserva + "' foi encontrado.");
    }
}
