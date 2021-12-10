package com.lgasolucoesemti.desafiodev.tipoTransacao.repository;

import com.lgasolucoesemti.desafiodev.tipoTransacao.model.TipoTransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tipoTransacaoRepository extends JpaRepository<TipoTransacaoModel, Long> {
}
