package io.github.cwireset.tcc.domain.reserva.adapter;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import io.github.cwireset.tcc.domain.reserva.response.InformacaoReservaResponse;

public class ReservaResponseAdapter {

    public static InformacaoReservaResponse converteReservaParaResponse(Reserva reserva) {
        InformacaoReservaResponse response = new InformacaoReservaResponse();

        response.setIdReserva(reserva.getId());

        response.setSolicitante(DadosSolicitanteAdapter.converterSolicitanteParaResponse(reserva.getSolicitante()));
        response.setQuantidadePessoas(reserva.getQuantidadePessoas());
        response.setAnuncio(DadosAnuncioAdapter.converterAnuncioParaResponce(reserva.getAnuncio()));
        response.setPeriodo(reserva.getPeriodo());
        response.setPagamento(reserva.getPagamento());

        return response;
    }
}
