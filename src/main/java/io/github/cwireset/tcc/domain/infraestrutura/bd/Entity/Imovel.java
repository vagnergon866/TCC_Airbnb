package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE IMOVEL SET deletar = true WHERE id=?")
@Where(clause = "deletar=false")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacao;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    @Valid
    private Usuario proprietario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_imovel")
    @Valid
    private List<CaracteristicaImovel> caracteristicas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean deletar;

    public Imovel(String identificacao, TipoImovel tipoImovel, Endereco endereco, List<CaracteristicaImovel> caracteristicas, Usuario proprietario) {
        this.identificacao = identificacao;
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.caracteristicas = caracteristicas;
    }


}
