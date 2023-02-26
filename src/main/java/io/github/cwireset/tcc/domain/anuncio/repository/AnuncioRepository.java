package io.github.cwireset.tcc.domain.anuncio.repository;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    Page<Anuncio> findAllByAnunciante_Id(Long idAnunciante, Pageable pageable);

    boolean existsByImovelId(Long idImovel);
}
