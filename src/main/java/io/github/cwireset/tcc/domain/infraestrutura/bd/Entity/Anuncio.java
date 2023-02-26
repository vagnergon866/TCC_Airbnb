package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE ANUNCIO SET deletar = true WHERE id=?")
@Where(clause = "deletar=false")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoAnuncio tipoAnuncio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_anunciante")
    private Usuario anunciante;

    private BigDecimal valorDiaria;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<FormaPagamento> formasAceitas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean deletar;

    private String descricao;

    public Anuncio(TipoAnuncio tipoAnuncio, BigDecimal valorDiaria, List<FormaPagamento> formasAceitas,
                   String descricao , Imovel imovel , Usuario anunciante) {
        this.tipoAnuncio = tipoAnuncio;
        this.imovel = imovel;
        this.anunciante = anunciante;
        this.valorDiaria = valorDiaria;
        this.formasAceitas = formasAceitas;
        this.descricao = descricao;
    }

    public Anuncio(Long id, Imovel imovel, Usuario anunciante, List<FormaPagamento> formasAceitas, String descricao) {
    }
}
