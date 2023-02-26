package io.github.cwireset.tcc.domain.reserva.response;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Pagamento;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Periodo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoReservaResponse {

    private Long idReserva;

    private DadosSolicitanteResponse solicitante;

    private Integer quantidadePessoas;

    private DadosAnuncioResponse anuncio;

    private Periodo periodo;

    private Pagamento pagamento;

}
