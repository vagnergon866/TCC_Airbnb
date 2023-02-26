package io.github.cwireset.tcc.domain.reserva.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiariaMenorQueUmDiaException extends RuntimeException {

    public DiariaMenorQueUmDiaException(){
        super("Período inválido! O número mínimo de diárias precisa ser maior ou igual à 1.");
    }
}
