package io.github.cwireset.tcc.domain.usuario.controller;


import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class BuscaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario buscarUsuarioPorCpf(@PathVariable("cpf") String cpf) {
        return usuarioService.bucarUsuarioPorCpf(cpf);
    }


}