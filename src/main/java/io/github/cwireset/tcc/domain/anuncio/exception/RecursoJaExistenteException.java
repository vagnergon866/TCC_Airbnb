package io.github.cwireset.tcc.domain.anuncio.exception;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecursoJaExistenteException extends RuntimeException {
    public RecursoJaExistenteException(Class clazz, Object id, String descricao) {
        super(String.format("Já existe um recurso do tipo %s com %s com o valor '%s'.", clazz.getSimpleName(), descricao, id));
    }
    public RecursoJaExistenteException(String message) {
        super(message);
    }
}
