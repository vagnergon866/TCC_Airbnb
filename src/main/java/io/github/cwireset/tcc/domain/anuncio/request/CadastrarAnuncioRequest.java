package io.github.cwireset.tcc.domain.anuncio.request;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarAnuncioRequest {

    @NotNull
    private Long idImovel;

    @NotNull
    private Long idAnunciante;

    @NotNull
    private TipoAnuncio tipoAnuncio;

    @NotNull
    private BigDecimal valorDiaria;

    @NotNull
    @Enumerated(EnumType.STRING)
    private List<FormaPagamento> formasAceitas;

    @NotNull
    private String descricao;

    public Anuncio converteParaObjeto(CadastrarAnuncioRequest cadastrarAnuncioRequest, Imovel imovel, Usuario usuario){
        return new Anuncio(
                cadastrarAnuncioRequest.getTipoAnuncio(),
                cadastrarAnuncioRequest.getValorDiaria(),
                cadastrarAnuncioRequest.getFormasAceitas(),
                cadastrarAnuncioRequest.getDescricao(),
                imovel,usuario);
    }

}
