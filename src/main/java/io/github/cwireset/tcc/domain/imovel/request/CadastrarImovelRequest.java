package io.github.cwireset.tcc.domain.imovel.request;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.*;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @NotNull
    @Valid
    private Endereco endereco;

    @NotNull
    private String identificacao;

    @NotNull
    private Long idProprietario;

    private List<CaracteristicaImovel> caracteristicas;


    public Imovel converteParaObjeto(CadastrarImovelRequest cadastrarImovelRequest, Usuario usuario){
        return new Imovel(
                cadastrarImovelRequest.getIdentificacao(),
                cadastrarImovelRequest.getTipoImovel(),
                cadastrarImovelRequest.getEndereco(),
                cadastrarImovelRequest.getCaracteristicas(),
                usuario);
    }

}
