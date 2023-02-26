package io.github.cwireset.tcc.domain.reserva.response;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.FormaPagamento;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import javafx.scene.NodeBuilder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosAnuncioResponse {

    private Long id;

    private Imovel imovel;

    private Usuario anunciante;

    private List<FormaPagamento> formasAceitas;

    private String descricao;

}
