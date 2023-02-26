package io.github.cwireset.tcc.domain.anuncio.controller;

import io.github.cwireset.tcc.domain.anuncio.service.AnuncioImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
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
@RequestMapping("/anuncios")
public class ListarAnuncioController {

    @Autowired
    private AnuncioImovelService anuncioImovelService;

    @GetMapping
    public Page<Anuncio> listarAnuncios(@PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC)@ApiIgnore Pageable pageable){
        return anuncioImovelService.listarAnuncios(pageable);
    }

    @GetMapping(path = "/anunciantes/{idAnunciante}")
    public Page<Anuncio> listarAnuncioPorIdAnunciante(@PathVariable("idAnunciante") Long idAnunciante,
                                                    @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC)
                                                    @ApiIgnore Pageable pageable){
        Page<Anuncio> anuncioPorAnunciante = anuncioImovelService.listarAnuncioPorIdAnunciante(idAnunciante,pageable);
        return anuncioPorAnunciante;
    }

}
