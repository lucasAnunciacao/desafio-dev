package com.lgasolucoesemti.desafiodev.cnab.repository;

import com.lgasolucoesemti.desafiodev.cnab.model.CnabModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CnabRepository extends JpaRepository<CnabModel, UUID> {

    @Query(value = "SELECT nome_loja FROM cnab group by nome_loja", nativeQuery = true)
    List<String> findNomeLojaByOrderByNomeLojaDesc();
    List<CnabModel> findCnabByNomeLoja(String nomeLoja);
}
