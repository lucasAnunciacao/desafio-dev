package com.lgasolucoesemti.desafiodev.utils;

import com.lgasolucoesemti.desafiodev.utils.execeptionUtils.BusinessValidateExepction;

public class ValidateUtils {

    public static boolean validateStringCpf(String cpf) {
        try {
            if (cpf.length() < 12 && Long.parseLong(cpf) > 0) {
                return true;
            }
        } catch (NumberFormatException numberFormatException) {
            throw new BusinessValidateExepction("Cpf inválido. Padrão de entrada de dados 00000000000");
        }
        return false;
    }

    public static boolean validateStringNumeroCartao(String numeroCartao) {
        try {
            if (numeroCartao.length() < 13 && Long.parseLong(numeroCartao) > 0) {
                return true;
            }
        } catch (NumberFormatException numberFormatException) {
            throw new BusinessValidateExepction("Número de cartão inválido. Padrão de entrada de dados 000000000000");
        }
        return false;
    }
}
