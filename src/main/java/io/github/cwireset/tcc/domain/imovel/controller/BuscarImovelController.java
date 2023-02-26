package io.github.cwireset.tcc.domain.imovel.controller;

import io.github.cwireset.tcc.domain.imovel.service.ImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/imoveis")
public class BuscarImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping(path = "/{idImovel}")
    public Imovel buscarImovelPorId(@PathVariable @Valid Long idImovel) {
        return imovelService.buscarImovelPorId(idImovel);
    }

}
