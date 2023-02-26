package io.github.cwireset.tcc.domain.reserva.controller;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Periodo;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import io.github.cwireset.tcc.domain.reserva.service.ListarReservaService;
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
@RequestMapping("/reservas")
public class ListarReservaController {

    @Autowired
    private ListarReservaService listarReservaService;


    @GetMapping(path = "/solicitantes/{idSolicitante}")
    public Page<Reserva> listarReservaPorIdSolicitanteOuPorPeriodo(@PathVariable("idSolicitante") Long idSolicitante, Periodo periodo,
                                                       @PageableDefault(sort = "periodo.dataHoraFinal", direction = Sort.Direction.DESC)
                                                                 @ApiIgnore Pageable pageable) {
        Page<Reserva> reservaPorSolicitante = listarReservaService.listarReservaPorIdSolicitanteOuPorPeriodo(idSolicitante, periodo, pageable);
        return reservaPorSolicitante;
    }

    @GetMapping(path = "/anuncios/anunciantes/{idAnunciante}")
    public Page<Reserva> listarReservaPorIdAnunciante(@PathVariable("idAnunciante") Long idAnunciante,
                                                      @PageableDefault(sort = "periodo.dataHoraFinal", direction = Sort.Direction.DESC)
                                                      @ApiIgnore Pageable pageable) {
        Page<Reserva> reservaPorAnunciante = listarReservaService.listarReservaPorIdAnunciante(idAnunciante, pageable);
        return reservaPorAnunciante;
    }

}
