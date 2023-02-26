package io.github.cwireset.tcc.domain.anuncio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnuncioReservadoException extends RuntimeException {

    public AnuncioReservadoException() {
        super("Este anuncio já esta reservado para o período informado.");
    }
}
