package com.lgasolucoesemti.desafiodev.cnab.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransacaoEmpresaDTO {

    public TransacaoEmpresaDTO(String descricaoTipoTransacao, String naturezaTipoTransacao, String sinalTipoTransacao,
                               Date dataOcorrencia, BigDecimal valorMovimentacao, String cpf, String cartaoTransacao) {
        this.descricaoTipoTransacao = descricaoTipoTransacao;
        this.naturezaTipoTransacao = naturezaTipoTransacao;
        this.sinalTipoTransacao = sinalTipoTransacao;
        this.dataOcorrencia = dataOcorrencia;
        this.valorMovimentacao = valorMovimentacao;
        this.cpf = cpf;
        this.cartaoTransacao = cartaoTransacao;
    }

    private String descricaoTipoTransacao;
    private String naturezaTipoTransacao;
    private String sinalTipoTransacao;
    private Date dataOcorrencia;
    private BigDecimal valorMovimentacao;
    private String cpf;
    private String cartaoTransacao;

    public String getDescricaoTipoTransacao() {
        return descricaoTipoTransacao;
    }

    public void setDescricaoTipoTransacao(String descricaoTipoTransacao) {
        this.descricaoTipoTransacao = descricaoTipoTransacao;
    }

    public String getNaturezaTipoTransacao() {
        return naturezaTipoTransacao;
    }

    public void setNaturezaTipoTransacao(String naturezaTipoTransacao) {
        this.naturezaTipoTransacao = naturezaTipoTransacao;
    }

    public String getSinalTipoTransacao() {
        return sinalTipoTransacao;
    }

    public void setSinalTipoTransacao(String sinalTipoTransacao) {
        this.sinalTipoTransacao = sinalTipoTransacao;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public BigDecimal getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(BigDecimal valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartaoTransacao() {
        return cartaoTransacao;
    }

    public void setCartaoTransacao(String cartaoTransacao) {
        this.cartaoTransacao = cartaoTransacao;
    }
}
