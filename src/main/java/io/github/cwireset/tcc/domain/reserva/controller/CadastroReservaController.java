package io.github.cwireset.tcc.domain.reserva.controller;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.FormaPagamento;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import io.github.cwireset.tcc.domain.reserva.adapter.ReservaResponseAdapter;
import io.github.cwireset.tcc.domain.reserva.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.domain.reserva.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.domain.reserva.service.CadastroReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class CadastroReservaController {

    @Autowired
    private CadastroReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse salvar(@RequestBody @Valid CadastrarReservaRequest request) {
        return reservaService.cadastrarReserva(request);
    }

    @PutMapping(path = "/{idReserva}/pagamentos")
    public void PagarReserva(@PathVariable("idReserva") Long idReserva,
                                @RequestBody @Valid FormaPagamento formaPagamento) {
        reservaService.PagarReserva(idReserva, formaPagamento);
    }

    @PutMapping(path = "/{idReserva}/pagamentos/cancelar")
    public void cancelarReserva(@PathVariable("idReserva") Long idReserva) {
        reservaService.cancelarReserva(idReserva);
    }

    @PutMapping(path = "/{idReserva}/pagamentos/estornar")
    public void estornarReserva(@PathVariable ("idReserva") Long idReserva) {
        reservaService.estornarReserva(idReserva);
    }

}
