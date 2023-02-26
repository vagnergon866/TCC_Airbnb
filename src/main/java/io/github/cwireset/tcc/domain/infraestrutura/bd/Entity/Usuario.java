package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotEmpty
    @Size(min = 11,max = 11, message = "O CPF deve ser informado no formato 99999999999.")
    @Pattern(regexp = "[0-9]+",message = "O CPF deve ser informado no formato 99999999999.")
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    private String avatar;

    public Usuario(String nome, String email, String senha, String cpf, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

}
