package io.github.cwireset.tcc.domain.reserva.service;

import io.github.cwireset.tcc.domain.anuncio.exception.AnuncioReservadoException;
import io.github.cwireset.tcc.domain.anuncio.service.AnuncioImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.*;
import io.github.cwireset.tcc.domain.reserva.excepton.*;
import io.github.cwireset.tcc.domain.reserva.repository.ReservaRepository;
import io.github.cwireset.tcc.domain.reserva.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.domain.reserva.response.DadosAnuncioResponse;
import io.github.cwireset.tcc.domain.reserva.response.DadosSolicitanteResponse;
import io.github.cwireset.tcc.domain.reserva.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class CadastroReservaService {

    private static final Integer MINIMO_DIARIAS_PARA_POUSADAS = 5;
    private static final Integer MINIMO_PESSOAS_PARA_HOTEIS = 2;
    private static final Integer QUANTIDADE_MINIMA_DIARIAS = 1;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnuncioImovelService anuncioImovelService;


    public InformacaoReservaResponse cadastrarReserva(CadastrarReservaRequest cadastrarReservaRequest) {
        defineHorarioDeEntradaESaida(cadastrarReservaRequest.getPeriodo());
        validarDatasPeriodo(cadastrarReservaRequest.getPeriodo());

        verificaSeExisteReservaAtivaParaImovel(cadastrarReservaRequest.getIdAnuncio(), cadastrarReservaRequest.getPeriodo());

        Anuncio anuncio = anuncioImovelService.buscarAnuncioPorId(cadastrarReservaRequest.getIdAnuncio());
        validarMinimoPessoas(anuncio.getImovel().getTipoImovel(), cadastrarReservaRequest.getQuantidadePessoas());
        validarMinimoDiarias(anuncio.getImovel().getTipoImovel(), cadastrarReservaRequest.getPeriodo());
        validaAnuncianteMesmoSolicitante(cadastrarReservaRequest, anuncio.getAnunciante());

        Usuario solicitante = usuarioService.buscarUsuarioPorId(cadastrarReservaRequest.getIdSolicitante());
        Pagamento pagamento = Pagamento.builder()
                .status(StatusPagamento.PENDENTE)
                .valorTotal(calcularValorReserva(cadastrarReservaRequest.getPeriodo(), anuncio.getValorDiaria()))
                .build();

        Reserva reserva = Reserva.builder()
                .quantidadePessoas(cadastrarReservaRequest.getQuantidadePessoas())
                .dataHoraReserva(LocalDateTime.now())
                .periodo(cadastrarReservaRequest.getPeriodo())
                .pagamento(pagamento)
                .anuncio(anuncio)
                .solicitante(solicitante)
                .build();


        reserva = reservaRepository.save(reserva);
        InformacaoReservaResponse informacaoReservaResponse = montarResponse(reserva);

        return informacaoReservaResponse;

    }

    private void defineHorarioDeEntradaESaida(Periodo periodo) {
        periodo.setDataHoraInicial(periodo.getDataHoraInicial()
                .with(ChronoField.HOUR_OF_DAY, 14)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0));

        periodo.setDataHoraFinal(periodo.getDataHoraFinal()
                .with(ChronoField.HOUR_OF_DAY, 12)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0));
    }

    private InformacaoReservaResponse montarResponse(Reserva reserva) {
        DadosAnuncioResponse anuncioResponse = DadosAnuncioResponse.builder()
                .id(reserva.getAnuncio().getId())
                .anunciante(reserva.getAnuncio().getAnunciante())
                .descricao(reserva.getAnuncio().getDescricao())
                .formasAceitas(reserva.getAnuncio().getFormasAceitas())
                .imovel(reserva.getAnuncio().getImovel())
                .build();

        DadosSolicitanteResponse solicitanteResponse = DadosSolicitanteResponse.builder()
                .id(reserva.getSolicitante().getId())
                .nome(reserva.getSolicitante().getNome())
                .build();

        InformacaoReservaResponse informacaoReservaResponse = InformacaoReservaResponse.builder()
                .idReserva(reserva.getId())
                .anuncio(anuncioResponse)
                .solicitante(solicitanteResponse)
                .quantidadePessoas(reserva.getQuantidadePessoas())
                .pagamento(reserva.getPagamento())
                .periodo(reserva.getPeriodo())
                .build();
        return informacaoReservaResponse;
    }

    private void validaAnuncianteMesmoSolicitante(CadastrarReservaRequest cadastrarReservaRequest, Usuario anunciante) {
        if (cadastrarReservaRequest.getIdSolicitante().equals(anunciante.getId())) {
            throw new SolicitanteNaoPodeSerProprioAnuncianteException();
        }
    }

    private void validarMinimoDiarias(TipoImovel tipoImovel, Periodo periodo) {
        if (TipoImovel.POUSADA.equals(tipoImovel)
                && periodo.quantidadeDiasNoPeriodo() < MINIMO_DIARIAS_PARA_POUSADAS) {
            throw new PeriodoMinimoException(MINIMO_DIARIAS_PARA_POUSADAS, tipoImovel);
        }
    }

    private void validarMinimoPessoas(TipoImovel tipoImovel, Integer quantidadePessoas) {
        if (TipoImovel.HOTEL.equals(tipoImovel) && quantidadePessoas < MINIMO_PESSOAS_PARA_HOTEIS) {
            throw new QuantidadeDePessoasParaHotelInvalidaException(MINIMO_PESSOAS_PARA_HOTEIS, tipoImovel);
        }
    }

    private void validarDatasPeriodo(Periodo periodo) {
        if (!periodo.dataFinalMaiorQueInicial()) {
            throw new PeriodoInvalidoException("A data final da reserva precisa ser maior do que a data inicial.");
        }

        if (periodo.quantidadeDiasNoPeriodo() < QUANTIDADE_MINIMA_DIARIAS) {
            throw new PeriodoInvalidoException(String.format("O número mínimo de diárias precisa ser maior ou igual à %d.", QUANTIDADE_MINIMA_DIARIAS));
        }
    }

    private void verificaSeExisteReservaAtivaParaImovel(Long idAnuncio, Periodo periodo) {
        List<StatusPagamento> statusPagamentos = Arrays.asList(StatusPagamento.CANCELADO, StatusPagamento.ESTORNADO);

        Boolean possuiSobrePosicao = reservaRepository.existsByAnuncioIdAndPeriodo_DataHoraInicialLessThanEqualAndPeriodo_DataHoraFinalGreaterThanEqualAndPagamentoStatusNotIn(idAnuncio, periodo.getDataHoraFinal(), periodo.getDataHoraInicial(), statusPagamentos);
        if (possuiSobrePosicao) {
            throw new AnuncioReservadoException();
        }
    }

    private BigDecimal calcularValorReserva(Periodo periodo, BigDecimal valorDiaria) {
        return valorDiaria.multiply(BigDecimal.valueOf(periodo.quantidadeDiasNoPeriodo()));
    }

    public void PagarReserva(Long idReserva, FormaPagamento formaPagamento) {

        Reserva reserva = reservaRepository.findById(idReserva).orElseThrow(() -> new IdReservaInvalidoException(idReserva));

        if (!reserva.getAnuncio().getFormasAceitas().contains(formaPagamento)) {
            throw new FormaDePagamentoInvalidaExcepton(formaPagamento, reserva);
        }

        if (reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO) || reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO)) {
            throw new PagamentoInvalidoException();
        }

        reserva.getPagamento().setFormaEscolhida(formaPagamento);
        reserva.getPagamento().setStatus(StatusPagamento.PAGO);

        reservaRepository.save(reserva);

    }

    public void cancelarReserva(Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva).orElseThrow(() -> new IdReservaInvalidoException(idReserva));

        if (reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO) || reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO)) {
            throw new CancelarReservaNaoDisponivelException();
        }

        reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);
        reservaRepository.save(reserva);

    }

    public void estornarReserva(Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva).orElseThrow(() -> new IdReservaInvalidoException(idReserva));

        if (reserva.getPagamento().getStatus().equals(StatusPagamento.PENDENTE) || reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO)) {
            throw new NaoFoiPossivelEstornarException();
        }

        reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
        reservaRepository.save(reserva);
    }


}
