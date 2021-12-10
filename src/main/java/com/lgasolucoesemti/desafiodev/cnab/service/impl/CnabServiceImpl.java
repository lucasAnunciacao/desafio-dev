package com.lgasolucoesemti.desafiodev.cnab.service.impl;

import com.lgasolucoesemti.desafiodev.cnab.model.CnabModel;
import com.lgasolucoesemti.desafiodev.cnab.model.dto.EmpresaDTO;
import com.lgasolucoesemti.desafiodev.cnab.model.dto.TransacaoEmpresaDTO;
import com.lgasolucoesemti.desafiodev.cnab.repository.CnabRepository;
import com.lgasolucoesemti.desafiodev.cnab.service.CnabService;
import com.lgasolucoesemti.desafiodev.tipoTransacao.model.enumeration.NaturezaTipoDocumentacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CnabServiceImpl implements CnabService {

    @Autowired
    private CnabRepository cnabRepository;

    @Override
    public List<CnabModel> findAll() {
        return cnabRepository.findAll();
    }

    @Override
    public List<EmpresaDTO> findAllOperationsAgreggateCompany() {
        List<EmpresaDTO> empresaDTOList = new ArrayList<>();
        List<String> empresasNome = cnabRepository.findNomeLojaByOrderByNomeLojaDesc();
        for (String empresaNome : empresasNome) {
            BigDecimal saldoEmpresa = BigDecimal.ZERO;
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setTransacaoEmpresaDTOList(new ArrayList<>());
            empresaDTO.setEmpresaNome(empresaNome);
            List<CnabModel> cnabModelList =  cnabRepository.findCnabByNomeLoja(empresaNome);
            if (cnabModelList.size() > 0) {
                empresaDTO.setEmpresaRepresentanteNome(cnabModelList.get(0).getNomeRepresentanteLoja());
            }
            for (CnabModel cnabModel : cnabModelList) {
                TransacaoEmpresaDTO transacaoEmpresaDTO =
                        new TransacaoEmpresaDTO(cnabModel.getTipoTransacaoModel().getDescricao(),
                                NaturezaTipoDocumentacaoEnum.forInt(cnabModel.getTipoTransacaoModel().getNatureza()).name(),
                                NaturezaTipoDocumentacaoEnum.forInt(cnabModel.getTipoTransacaoModel().getNatureza()).getSinal(),
                                cnabModel.getDataOcorrencia(), cnabModel.getValorMovimentacao(),
                                cnabModel.getCpf(), cnabModel.getCartaoTransacao());
                if (transacaoEmpresaDTO.getSinalTipoTransacao().equals("+")) {
                    saldoEmpresa = saldoEmpresa.add(transacaoEmpresaDTO.getValorMovimentacao());
                } else {
                    saldoEmpresa = saldoEmpresa.subtract(transacaoEmpresaDTO.getValorMovimentacao());
                }
                empresaDTO.getTransacaoEmpresaDTOList().add(transacaoEmpresaDTO);
            }
            empresaDTO.setSaldoEmpresa(saldoEmpresa);
            empresaDTOList.add(empresaDTO);
        }
        return empresaDTOList;
    }

    @Override
    public Optional<CnabModel> findById(UUID id) {
        return cnabRepository.findById(id);
    }

    @Override
    public List<CnabModel> parseCnabFile(MultipartFile multipartFile) {
        InputStream inputStream = null;
        List<CnabModel> cnabModels = new ArrayList<>();
        try {
            inputStream = multipartFile.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(s -> cnabModels.add(CnabModel.parseStringToCnab(s)));
            if (cnabModels.stream().filter(cnabModel -> cnabModel.getErroParse() != null
                    && cnabModel.getErroParse().length() > 0 ).count() == 0) {
                cnabRepository.saveAll(cnabModels);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cnabModels;
    }

    @Override
    public CnabModel update(CnabModel cnabModel) {
        return cnabRepository.save(cnabModel);
    }

    @Override
    public void delete(CnabModel cnabModel) {
        cnabRepository.delete(cnabModel);
    }

}
