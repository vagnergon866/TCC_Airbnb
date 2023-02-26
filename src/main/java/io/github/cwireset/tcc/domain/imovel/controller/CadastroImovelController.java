package io.github.cwireset.tcc.domain.imovel.controller;


import io.github.cwireset.tcc.domain.imovel.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.domain.imovel.service.ImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/imoveis")
public class CadastroImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel cadastrarImovel(@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest) {
        return this.imovelService.cadastrarImovel(cadastrarImovelRequest);
    }

    @DeleteMapping(path = "/{idImovel}")
    public void deletarImovelPorId(@PathVariable @Valid Long idImovel) {
        imovelService.deleteImovelPorId(idImovel);
    }


}
