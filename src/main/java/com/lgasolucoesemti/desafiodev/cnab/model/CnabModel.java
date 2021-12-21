package com.lgasolucoesemti.desafiodev.cnab.model;

import com.lgasolucoesemti.desafiodev.tipoTransacao.model.TipoTransacaoModel;
import com.lgasolucoesemti.desafiodev.utils.ValidateUtils;
import com.lgasolucoesemti.desafiodev.utils.execeptionUtils.BusinessValidateExepction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cnab")
public class CnabModel {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_transacao_id", nullable = false, referencedColumnName = "id")
    private TipoTransacaoModel tipoTransacaoModel;

    @Column(name = "dt_ocorrencia")
    private Date dataOcorrencia;

    @Column(name = "valor_movimentacao")
    private BigDecimal valorMovimentacao;

    @Column(name = "cpf_beneficiario")
    private String cpf;

    @Column(name = "cartao_transacao")
    private String cartaoTransacao;

    @Column(name = "nome_representante_loja")
    private String nomeRepresentanteLoja;

    @Column(name = "nome_loja")
    private String nomeLoja;

    private String erroParse;

    public static CnabModel parseStringToCnab(String cnabString) {
        CnabModel cnabModel = new CnabModel();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            if (cnabString.length() < 80) {
                return null;
            }
            if (Long.parseLong(cnabString.substring(0, 1)) > 0) {
                TipoTransacaoModel tipoTransacaoModel = new TipoTransacaoModel();
                tipoTransacaoModel.setId(Long.parseLong(cnabString.substring(0, 1)));
                cnabModel.setTipoTransacaoModel(tipoTransacaoModel);
            }
            if (dateFormat.parse(cnabString.substring(1, 9)) != null) {
                cnabModel.setDataOcorrencia(dateFormat.parse(cnabString.substring(1, 9)));
            }
            if (new BigDecimal(cnabString.substring(9, 19)).longValue() > 0) {
                cnabModel.setValorMovimentacao((new BigDecimal(cnabString.substring(9, 19))).divide(new BigDecimal(100)));
            }
            if (ValidateUtils.validateStringCpf(cnabString.substring(19, 30))) {
                cnabModel.setCpf(cnabString.substring(19, 30));
            }
            if (ValidateUtils.validateStringNumeroCartao(cnabString.substring(30, 42))) {
                cnabModel.setCartaoTransacao(cnabString.substring(30, 42));
            }

            cnabModel.getDataOcorrencia().setHours(Integer.parseInt(cnabString.substring(42, 44)));
            cnabModel.getDataOcorrencia().setMinutes(Integer.parseInt(cnabString.substring(44, 46)));
            cnabModel.getDataOcorrencia().setSeconds(Integer.parseInt(cnabString.substring(46, 48)));

            cnabModel.setNomeRepresentanteLoja(cnabString.substring(48, 62).stripTrailing());
            cnabModel.setNomeLoja(cnabString.substring(62, 80).stripTrailing());
            cnabModel.setId(UUID.randomUUID());
        } catch (ParseException parseException) {
            cnabModel.setErroParse("Erro ao validar tipo de transação.");
        } catch (NumberFormatException numberFormatException) {
            cnabModel.setErroParse("Erro ao validar valor da transação.");
        } catch (BusinessValidateExepction businessValidateExepction) {
            cnabModel.setErroParse(businessValidateExepction.getMensagem());
        }
        return cnabModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoTransacaoModel getTipoTransacaoModel() {
        return tipoTransacaoModel;
    }

    public void setTipoTransacaoModel(TipoTransacaoModel tipoTransacaoModel) {
        this.tipoTransacaoModel = tipoTransacaoModel;
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

    public String getNomeRepresentanteLoja() {
        return nomeRepresentanteLoja;
    }

    public void setNomeRepresentanteLoja(String nomeRepresentanteLoja) {
        this.nomeRepresentanteLoja = nomeRepresentanteLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getErroParse() {
        return erroParse;
    }

    public void setErroParse(String erroParse) {
        this.erroParse = erroParse;
    }
}
