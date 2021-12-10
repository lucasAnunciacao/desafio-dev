package com.lgasolucoesemti.desafiodev.cnab.service;

import com.lgasolucoesemti.desafiodev.cnab.model.CnabModel;
import com.lgasolucoesemti.desafiodev.cnab.model.dto.EmpresaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CnabService {
    List<CnabModel> findAll();
    List<EmpresaDTO> findAllOperationsAgreggateCompany();
    Optional<CnabModel> findById(UUID id);
    CnabModel update(CnabModel cnabModel);
    List<CnabModel> parseCnabFile(MultipartFile multipartFile);
    void delete(CnabModel cnabModel);
}
