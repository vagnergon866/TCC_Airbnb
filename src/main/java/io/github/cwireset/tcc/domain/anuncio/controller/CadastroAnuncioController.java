package io.github.cwireset.tcc.domain.anuncio.controller;

import io.github.cwireset.tcc.domain.anuncio.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.domain.anuncio.service.AnuncioImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/anuncios")
public class CadastroAnuncioController {

    @Autowired
    private AnuncioImovelService anuncioImovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio cadastrarAnuncioImovel(@RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest) {
        return anuncioImovelService.cadastrarAnuncioImovel(cadastrarAnuncioRequest);
    }

    @DeleteMapping(path = "/{idAnuncio}")
    public void deletarAnuncio(@PathVariable @Valid Long idAnuncio) {
        anuncioImovelService.deletarAnuncio(idAnuncio);
    }

}
