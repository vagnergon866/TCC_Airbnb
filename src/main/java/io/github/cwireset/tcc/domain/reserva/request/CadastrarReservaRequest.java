package io.github.cwireset.tcc.domain.reserva.request;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarReservaRequest {

    @NotNull
    private Long idSolicitante;

    @NotNull
    private Long idAnuncio;

    @NotNull
    private Periodo periodo;

    @NotNull
    private Integer quantidadePessoas;

//    public Reserva converteParaObjeto(CadastrarReservaRequest cadastrarReservaRequest, Usuario solicitante, Anuncio anuncio){
//        return new Reserva(solicitante, anuncio,
//                cadastrarReservaRequest.getPeriodo(),
//                cadastrarReservaRequest.getQuantidadePessoas()
//                );
//    }

}
