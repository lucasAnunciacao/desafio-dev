package com.lgasolucoesemti.desafiodev.tipoTransacao.model;

import com.lgasolucoesemti.desafiodev.tipoTransacao.model.enumeration.NaturezaTipoDocumentacaoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_transacao")
public class TipoTransacaoModel {

    @Id
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "natureza_trasacao")
    private Integer natureza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNatureza() {
        return natureza;
    }

    public void setNatureza(Integer natureza) {
        this.natureza = natureza;
    }
}
