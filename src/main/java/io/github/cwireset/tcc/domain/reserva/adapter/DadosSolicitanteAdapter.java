package io.github.cwireset.tcc.domain.reserva.adapter;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.reserva.response.DadosSolicitanteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DadosSolicitanteAdapter {

    public  static DadosSolicitanteResponse converterSolicitanteParaResponse(Usuario solicitante){
        DadosSolicitanteResponse response = new DadosSolicitanteResponse();

        response.setId(solicitante.getId());
        response.setNome(solicitante.getNome());

        return response;
    }

}
