package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoNaoDisponivelParaReserva extends RuntimeException {

    public PeriodoNaoDisponivelParaReserva(){
        super("Este anuncio já esta reservado para o período informado.");
    }
}
