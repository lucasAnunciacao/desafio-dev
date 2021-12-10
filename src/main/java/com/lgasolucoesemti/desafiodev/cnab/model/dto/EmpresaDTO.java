package com.lgasolucoesemti.desafiodev.cnab.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmpresaDTO {

    private String EmpresaNome;
    private String EmpresaRepresentanteNome;
    private List<TransacaoEmpresaDTO> transacaoEmpresaDTOList;
    private BigDecimal saldoEmpresa;

    public String getEmpresaNome() {
        return EmpresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.EmpresaNome = empresaNome;
    }

    public String getEmpresaRepresentanteNome() {
        return EmpresaRepresentanteNome;
    }

    public void setEmpresaRepresentanteNome(String empresaRepresentanteNome) {
        this.EmpresaRepresentanteNome = empresaRepresentanteNome;
    }

    public List<TransacaoEmpresaDTO> getTransacaoEmpresaDTOList() {
        return transacaoEmpresaDTOList;
    }

    public void setTransacaoEmpresaDTOList(List<TransacaoEmpresaDTO> transacaoEmpresaDTOList) {
        this.transacaoEmpresaDTOList = transacaoEmpresaDTOList;
    }

    public BigDecimal getSaldoEmpresa() {
        return saldoEmpresa;
    }

    public void setSaldoEmpresa(BigDecimal saldoEmpresa) {
        this.saldoEmpresa = saldoEmpresa;
    }
}
