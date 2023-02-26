package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoImovel {

    APARTAMENTO("Apartamentro"),
    CASA("Casa"),
    HOTEL("Hotel"),
    POUSADA("Pousada");

    private String descricao;



}
