package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String numero;

    private String complemento;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String estado;

}
