package io.github.cwireset.tcc.domain.reserva.excepton;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.TipoImovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeDePessoasParaHotelInvalidaException extends RuntimeException {

    public QuantidadeDePessoasParaHotelInvalidaException(Integer quantidadeDePessoas, TipoImovel tipoImovel) {
        super("Não é possivel realizar uma reserva com menos de " + quantidadeDePessoas + " pessoas para imóveis do tipo " + tipoImovel.getDescricao());
    }
}
