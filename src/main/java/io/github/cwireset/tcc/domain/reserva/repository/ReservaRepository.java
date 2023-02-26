package io.github.cwireset.tcc.domain.reserva.repository;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Reserva;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Boolean existsByAnuncioIdAndPeriodo_DataHoraInicialLessThanEqualAndPeriodo_DataHoraFinalGreaterThanEqualAndPagamentoStatusNotIn(Long idAnuncio, LocalDateTime dataHoraFinal, LocalDateTime dataHoraInicial, List<StatusPagamento> status);

    Page<Reserva> findAllBySolicitanteIdAndPeriodoDataHoraInicialGreaterThanEqualAndPeriodoDataHoraFinalLessThanEqual(Long idSolicitante, LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Pageable pageable);

    Page<Reserva> findAllBySolicitanteId(Long idSolicitante, Pageable pageable);

    Page<Reserva> findAllByAnuncioAnuncianteId(Long idAnunciante, Pageable pageable);
}
