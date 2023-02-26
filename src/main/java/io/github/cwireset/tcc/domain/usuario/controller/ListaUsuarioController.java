package io.github.cwireset.tcc.domain.usuario.controller;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/usuarios")
public class ListaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public Page<Usuario> listarUsuarios(@PageableDefault(sort = "nome")@ApiIgnore Pageable pageable){
        return usuarioService.listarUsuarios(pageable);

    }

}