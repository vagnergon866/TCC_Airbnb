package io.github.cwireset.tcc.domain.anuncio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdAnuncioInvalidoException extends RuntimeException {

    public IdAnuncioInvalidoException(Long idAnuncio) {
        super("Nenhum(a) Anuncio com Id com o valor '" + idAnuncio + "' foi encontrado.");
    }
}
