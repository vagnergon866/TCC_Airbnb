package io.github.cwireset.tcc.domain.imovel.service;


import io.github.cwireset.tcc.domain.anuncio.service.AnuncioImovelService;
import io.github.cwireset.tcc.domain.imovel.exception.IdImovelInvalidoException;
import io.github.cwireset.tcc.domain.imovel.exception.ImovelComAnuncioException;
import io.github.cwireset.tcc.domain.imovel.repository.ImovelRepository;
import io.github.cwireset.tcc.domain.imovel.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioImovelService anuncioImovelService;


    public Imovel cadastrarImovel(CadastrarImovelRequest cadastrarImovelRequest) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(cadastrarImovelRequest.getIdProprietario());
        Imovel imovel = new CadastrarImovelRequest().converteParaObjeto(cadastrarImovelRequest, usuario);

        return imovelRepository.save(imovel);
    }

    public Page<Imovel> listarImovel(Pageable pageable) {
        return imovelRepository.findAll(pageable);
    }

    public Page<Imovel> listarPorIdProprietario(Long idPreoprietario, Pageable pageable) {
        return imovelRepository.findAllByProprietario_Id(idPreoprietario, pageable);
    }

    public Imovel buscarImovelPorId(Long idImovel) {
        return imovelRepository.findById(idImovel).orElseThrow(() -> new IdImovelInvalidoException(idImovel));
    }

    public void deleteImovelPorId(Long idImovel) {
        Imovel imovel = imovelRepository.getById(idImovel);

        if (!imovelRepository.existsById(idImovel)) {
            throw new IdImovelInvalidoException(idImovel);
        }
        if (anuncioImovelService.anuncioPossuiImovel(imovel)) {
            throw new ImovelComAnuncioException();
        }
        imovel.setDeletar(true);
        imovelRepository.save(imovel);
    }
}
