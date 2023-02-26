package io.github.cwireset.tcc.domain.reserva.excepton;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.FormaPagamento;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaDePagamentoInvalidaExcepton extends RuntimeException {

    public FormaDePagamentoInvalidaExcepton( FormaPagamento formaPagamento , Reserva reserva){
        super("O anúncio não aceita "
                + formaPagamento + " como forma de pagamento. As formas aceitas são "
                + reserva.getAnuncio().getFormasAceitas().toString().replace("[","").replace("]","") + ".");
    }
}
