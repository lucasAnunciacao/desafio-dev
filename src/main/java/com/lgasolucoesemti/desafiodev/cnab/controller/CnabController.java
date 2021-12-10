package com.lgasolucoesemti.desafiodev.cnab.controller;

import com.lgasolucoesemti.desafiodev.cnab.model.CnabModel;
import com.lgasolucoesemti.desafiodev.cnab.model.dto.EmpresaDTO;
import com.lgasolucoesemti.desafiodev.cnab.service.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @RequestMapping(value = "/cnab", method = RequestMethod.GET)
    public ResponseEntity<List<CnabModel>> findAll() {
        List<CnabModel> cnabModelList = cnabService.findAll();
        return ResponseEntity.ok(cnabModelList);
    }

    @RequestMapping(value = "/cnab/operations/agreggate/company", method = RequestMethod.GET)
    public ResponseEntity<List<EmpresaDTO>> findAllOperationsAgreggateCompany() {
        List<EmpresaDTO> empresaDTOList = cnabService.findAllOperationsAgreggateCompany();
        return ResponseEntity.ok(empresaDTOList);
    }

    @RequestMapping(value = "/cnab/{id}", method = RequestMethod.GET)
    public ResponseEntity<CnabModel> findById(@PathVariable UUID id) {
        Optional<CnabModel> optionalCnabModel = cnabService.findById(id);
        return ResponseEntity.ok(optionalCnabModel.orElse(null));
    }

    @RequestMapping(value = "/cnab/file", method = RequestMethod.POST)
    public ResponseEntity<List<CnabModel>> createFromFile(@RequestParam("file") MultipartFile file) {
        List<CnabModel> cnabModelResponse = cnabService.parseCnabFile(file);
        if (cnabModelResponse.stream().filter(cnabModel -> cnabModel.getErroParse() != null
                && cnabModel.getErroParse().length() > 0 ).count() == 0) {
            return ResponseEntity.ok(cnabModelResponse);
        }
        return ResponseEntity.badRequest().body(cnabModelResponse);
    }

    @RequestMapping(value = "/cnab", method = RequestMethod.POST)
    public ResponseEntity<CnabModel> create(@RequestBody CnabModel cnabModel) {
        CnabModel cnabModelResponse = cnabService.update(cnabModel);
        return ResponseEntity.ok(cnabModelResponse);
    }

    @RequestMapping(value = "/cnab", method = RequestMethod.PUT)
    public ResponseEntity<CnabModel> update(@RequestBody CnabModel cnabModel) {
        CnabModel cnabModelResponse = cnabService.update(cnabModel);
        return ResponseEntity.ok(cnabModelResponse);
    }

    @RequestMapping(value = "/cnab/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        CnabModel cnabModel = new CnabModel();
        cnabModel.setId(UUID.fromString(id));
        cnabService.delete(cnabModel);
    }
}
