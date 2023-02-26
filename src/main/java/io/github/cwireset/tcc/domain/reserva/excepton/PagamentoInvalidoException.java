package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PagamentoInvalidoException extends RuntimeException {

    public PagamentoInvalidoException(){
        super("Não é possível realizar o pagamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
