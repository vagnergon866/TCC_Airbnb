package io.github.cwireset.tcc.domain.reserva.adapter;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import io.github.cwireset.tcc.domain.reserva.response.DadosAnuncioResponse;
import lombok.Setter;


public class DadosAnuncioAdapter {

    public static DadosAnuncioResponse converterAnuncioParaResponce(Anuncio anuncio){
        DadosAnuncioResponse response = new DadosAnuncioResponse();

        response.setId(anuncio.getId());
        response.setImovel(anuncio.getImovel());
        response.setAnunciante(anuncio.getAnunciante());
        response.setFormasAceitas(anuncio.getFormasAceitas());
        response.setDescricao(anuncio.getDescricao());

        return response;
    }

}
