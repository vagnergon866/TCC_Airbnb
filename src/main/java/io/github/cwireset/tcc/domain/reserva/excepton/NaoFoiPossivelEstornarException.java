package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoFoiPossivelEstornarException extends RuntimeException {

    public NaoFoiPossivelEstornarException(){
        super("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
    }
}
