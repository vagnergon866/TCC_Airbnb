package io.github.cwireset.tcc.domain.imovel.controller;

import io.github.cwireset.tcc.domain.imovel.service.ImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/imoveis")
public class ListaImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping
    public Page<Imovel> listarImovel(@PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC)@ApiIgnore Pageable pageable){
        return imovelService.listarImovel(pageable);
    }
    @GetMapping(path = "/proprietarios/{idProprietario}")
    public Page<Imovel> listarImoverPorIdPropietario(@PathVariable("idProprietario") Long idProprietario,
                                                   @PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC)
                                                   @ApiIgnore Pageable pageable) {
        Page<Imovel> imovelProprietario = imovelService.listarPorIdProprietario(idProprietario, pageable);
        return imovelProprietario;

    }

}
