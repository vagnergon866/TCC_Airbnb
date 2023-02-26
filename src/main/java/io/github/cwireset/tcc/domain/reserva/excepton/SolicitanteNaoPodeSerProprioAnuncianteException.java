package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitanteNaoPodeSerProprioAnuncianteException extends RuntimeException {

    public SolicitanteNaoPodeSerProprioAnuncianteException(){
        super("O solicitante de uma reserva não pode ser o próprio anunciante.");
    }
}
