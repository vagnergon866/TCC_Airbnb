package io.github.cwireset.tcc.domain.usuario.request;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Endereco;
import lombok.*;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    private Endereco endereco;


}
