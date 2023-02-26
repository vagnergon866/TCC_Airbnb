package io.github.cwireset.tcc.domain.infraestrutura.bd.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Periodo {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraInicial;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraFinal;

    public Long quantidadeDiasNoPeriodo() {
        return Duration.between(dataHoraInicial, dataHoraFinal.plus(2, ChronoUnit.HOURS)).toDays();
    }

    public boolean dataFinalMaiorQueInicial() {
        return dataHoraFinal.isAfter(dataHoraInicial);
    }
}
