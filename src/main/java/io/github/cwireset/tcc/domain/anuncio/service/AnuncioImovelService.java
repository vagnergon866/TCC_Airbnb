package io.github.cwireset.tcc.domain.anuncio.service;

import io.github.cwireset.tcc.domain.anuncio.exception.IdAnuncioInvalidoException;
import io.github.cwireset.tcc.domain.anuncio.exception.RecursoJaExistenteException;
import io.github.cwireset.tcc.domain.anuncio.repository.AnuncioRepository;
import io.github.cwireset.tcc.domain.anuncio.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.domain.imovel.service.ImovelService;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnuncioImovelService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private UsuarioService usuarioService;


    public Anuncio cadastrarAnuncioImovel(CadastrarAnuncioRequest cadastrarAnuncioRequest) {
        Imovel imovel = imovelService.buscarImovelPorId(cadastrarAnuncioRequest.getIdImovel());
        Usuario usuario = usuarioService.buscarUsuarioPorId(cadastrarAnuncioRequest.getIdAnunciante());
        Anuncio anuncio = new CadastrarAnuncioRequest().converteParaObjeto(cadastrarAnuncioRequest, imovel, usuario);

        if (anuncioRepository.existsByImovelId(cadastrarAnuncioRequest.getIdImovel())) {
            throw new RecursoJaExistenteException(Anuncio.class, cadastrarAnuncioRequest.getIdImovel(), "IdImovel");
        }

        return anuncioRepository.save(anuncio);
    }

    public Page<Anuncio> listarAnuncios(Pageable pageable) {
        return anuncioRepository.findAll(pageable);
    }

    public Page<Anuncio> listarAnuncioPorIdAnunciante(Long idAnunciante, Pageable pageable) {
        return anuncioRepository.findAllByAnunciante_Id(idAnunciante, pageable);
    }

    public boolean anuncioPossuiImovel(Imovel imovel) {
        return anuncioRepository.existsById(imovel.getId());
    }

    public Anuncio buscarAnuncioPorId(Long idAnuncio) {
        if (!anuncioRepository.existsById(idAnuncio)) {
            throw new IdAnuncioInvalidoException(idAnuncio);
        }
        return anuncioRepository.getById(idAnuncio);
    }

    public void deletarAnuncio(Long idAnuncio) {
        Anuncio anuncio = anuncioRepository.getById(idAnuncio);

        if (!anuncioRepository.existsById(idAnuncio)) {
            throw new IdAnuncioInvalidoException(idAnuncio);
        }
        anuncio.setDeletar(true);
        anuncioRepository.save(anuncio);
    }

}
