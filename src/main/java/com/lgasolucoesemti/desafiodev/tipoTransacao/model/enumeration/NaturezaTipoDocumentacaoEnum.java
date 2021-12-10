package com.lgasolucoesemti.desafiodev.tipoTransacao.model.enumeration;

public enum NaturezaTipoDocumentacaoEnum {
    ENTRADA (0) ,
    SAIDA (1);

    private final int code;

    NaturezaTipoDocumentacaoEnum(int code) {
        this.code = code;
    }

    public static NaturezaTipoDocumentacaoEnum forInt(int code) {
        NaturezaTipoDocumentacaoEnum[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            NaturezaTipoDocumentacaoEnum type = arr$[i$];
            if (type.code == code) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid CellType code: " + code);
    }

    public int getCode() {
        return this.code;
    }

    public String getSinal() {
        if (this.code == 0 ) {
            return "+";
        } else {
            return  "-";
        }
    }
}
