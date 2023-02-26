package io.github.cwireset.tcc.domain.reserva.service;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Periodo;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import io.github.cwireset.tcc.domain.reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Page<Reserva> listarReservaPorIdSolicitanteOuPorPeriodo(Long idSolicitante, Periodo periodo, Pageable pageable) {
        if (periodo == null || periodo.getDataHoraInicial() == null || periodo.getDataHoraFinal() == null) {
            return reservaRepository.findAllBySolicitanteId(idSolicitante, pageable);
        }

        return reservaRepository.findAllBySolicitanteIdAndPeriodoDataHoraInicialGreaterThanEqualAndPeriodoDataHoraFinalLessThanEqual(idSolicitante, periodo.getDataHoraInicial(), periodo.getDataHoraFinal(), pageable);
    }

    public Page<Reserva> listarReservaPorIdAnunciante(Long idAnunciante, Pageable pageable) {
        return reservaRepository.findAllByAnuncioAnuncianteId(idAnunciante, pageable);
    }
}
