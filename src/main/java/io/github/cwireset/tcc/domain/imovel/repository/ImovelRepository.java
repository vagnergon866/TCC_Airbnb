package io.github.cwireset.tcc.domain.imovel.repository;

import io.github.cwireset.tcc.domain.infraestrutura.bd.Entity.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {


    Page<Imovel> findAllByProprietario_Id(Long idPreoprietario, Pageable pageable);


}

