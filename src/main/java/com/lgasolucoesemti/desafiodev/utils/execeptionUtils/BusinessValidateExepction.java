package com.lgasolucoesemti.desafiodev.utils.execeptionUtils;

public class BusinessValidateExepction extends RuntimeException {

    private String mensagem;

    public BusinessValidateExepction(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
