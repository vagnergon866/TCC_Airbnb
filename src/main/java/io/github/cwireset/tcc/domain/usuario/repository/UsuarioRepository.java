package io.github.cwireset.tcc.domain.usuario.repository;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Usuario findByCpf(String cpf);

    boolean existsByEmailAndIdNot(String email, Long id);
}