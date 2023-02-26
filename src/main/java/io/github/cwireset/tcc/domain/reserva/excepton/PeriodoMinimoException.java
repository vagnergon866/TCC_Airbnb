package io.github.cwireset.tcc.domain.reserva.excepton;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.TipoImovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoMinimoException extends RuntimeException {

    public PeriodoMinimoException(Integer minimoPessoas, TipoImovel tipoImovel) {
        super(String.format("Não é possivel realizar uma reserva com menos de %d diárias para imóveis do tipo %s", minimoPessoas, tipoImovel.getDescricao()));
    }
}
