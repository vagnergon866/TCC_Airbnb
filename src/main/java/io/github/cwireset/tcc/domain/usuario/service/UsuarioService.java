package io.github.cwireset.tcc.domain.usuario.service;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import io.github.cwireset.tcc.domain.usuario.exception.CpfDublicadoExeption;
import io.github.cwireset.tcc.domain.usuario.exception.CpfInvalidoException;
import io.github.cwireset.tcc.domain.usuario.exception.EmailDuplicadoException;
import io.github.cwireset.tcc.domain.usuario.exception.IdInvalidoException;
import io.github.cwireset.tcc.domain.usuario.repository.AvatarRepository;
import io.github.cwireset.tcc.domain.usuario.repository.UsuarioRepository;
import io.github.cwireset.tcc.domain.usuario.request.AtualizarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailDuplicadoException(usuario.getEmail());
        }

        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new CpfDublicadoExeption(usuario.getCpf());
        }

//        String avatar = avatarRepository.getAvatar().toString();
//        usuario.setAvatar(avatar);

       return usuarioRepository.save(usuario);
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IdInvalidoException(id));
    }

    public Usuario bucarUsuarioPorCpf(String cpf) {
        if (!usuarioRepository.existsByCpf(cpf)){
            throw new CpfInvalidoException(cpf);
        }
      return usuarioRepository.findByCpf(cpf);
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (usuarioRepository.existsByEmailAndIdNot(atualizarUsuarioRequest.getEmail(),id)){
            throw new EmailDuplicadoException(atualizarUsuarioRequest.getEmail());
        }

        Usuario usuarioAtual = buscarUsuarioPorId(id);

        usuarioAtual.setNome(atualizarUsuarioRequest.getNome());
        usuarioAtual.setSenha(atualizarUsuarioRequest.getSenha());
        usuarioAtual.setDataNascimento(atualizarUsuarioRequest.getDataNascimento());
        usuarioAtual.setEndereco(atualizarUsuarioRequest.getEndereco());
        usuarioAtual.setEmail(atualizarUsuarioRequest.getEmail());

        if (atualizarUsuarioRequest.getEndereco() != null) {
            usuarioAtual.getEndereco().setBairro(atualizarUsuarioRequest.getEndereco().getBairro());
            usuarioAtual.getEndereco().setCep(atualizarUsuarioRequest.getEndereco().getCep());
            usuarioAtual.getEndereco().setComplemento(atualizarUsuarioRequest.getEndereco().getComplemento());
            usuarioAtual.getEndereco().setCidade(atualizarUsuarioRequest.getEndereco().getCidade());
            usuarioAtual.getEndereco().setEstado(atualizarUsuarioRequest.getEndereco().getEstado());
            usuarioAtual.getEndereco().setLogradouro(atualizarUsuarioRequest.getEndereco().getLogradouro());
            usuarioAtual.getEndereco().setNumero(atualizarUsuarioRequest.getEndereco().getNumero());
            } else {
                usuarioAtual.setEndereco(null);
            }

        return usuarioRepository.save(usuarioAtual);
    }
}
