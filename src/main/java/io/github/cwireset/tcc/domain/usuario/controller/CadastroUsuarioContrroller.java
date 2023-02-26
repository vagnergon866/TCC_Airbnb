package io.github.cwireset.tcc.domain.usuario.controller;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioContrroller {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody @Valid Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);

    }

    @PutMapping(path = "/{id}")
    public Usuario atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) {
        return usuarioService.atualizarUsuario(id, atualizarUsuarioRequest);

    }

}
