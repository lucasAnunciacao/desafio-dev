package com.lgasolucoesemti.desafiodev.service.impl;

import com.lgasolucoesemti.desafiodev.cnab.model.CnabModel;
import com.lgasolucoesemti.desafiodev.cnab.repository.CnabRepository;
import com.lgasolucoesemti.desafiodev.cnab.service.impl.CnabServiceImpl;
import com.lgasolucoesemti.desafiodev.tipoTransacao.model.TipoTransacaoModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CnabServiceImplTest {

    @Mock
    private CnabRepository cnabRepository;

    @InjectMocks
    private CnabServiceImpl cnabService;

    @Test
    public void validateFindAll() {
        Mockito.when(cnabRepository.findAll()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(cnabService.findAll());
    }

    @Test
    public void validateFindById() {
        CnabModel cnabModel = new CnabModel();
        UUID uuid = UUID.randomUUID();
        cnabModel.setId(uuid);
        Mockito.when(cnabRepository.findById(uuid)).thenReturn(java.util.Optional.of(cnabModel));
        Assert.assertEquals(cnabService.findById(uuid), java.util.Optional.of(cnabModel) );
    }

    @Test
    public void validateSalvar() throws Exception {
        CnabModel cnabModel = new CnabModel();
        Mockito.when(cnabRepository.save(cnabModel)).thenReturn(cnabModel);
        Assert.assertEquals(cnabService.update(cnabModel), cnabModel);
    }

    @Test
    public void validateDelete() {
        CnabModel cnabModel = new CnabModel();
        cnabService.delete(cnabModel);
    }

    @Test
    public void validateFindAllOperationsAgreggateCompany() {
        String empresaNome = "testeEmpresa";
        List<String> empresasNome = Arrays.asList(empresaNome);
        Mockito.when(cnabRepository.findNomeLojaByOrderByNomeLojaDesc()).thenReturn(empresasNome);
        Mockito.when(cnabRepository.findCnabByNomeLoja(empresaNome)).thenReturn(Arrays.asList(createCnab(empresaNome, 0), createCnab(empresaNome, 1)));
        Assert.assertFalse(cnabService.findAllOperationsAgreggateCompany().isEmpty());
    }

    @Test
    public void validateParseCnabFile() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("cnab.txt");
        MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", is);
        cnabService.parseCnabFile(file);
    }

    @Test
    public void validateParseCnabFileError() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("cnab_error.txt");
        MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", is);
        Assert.assertFalse(cnabService.parseCnabFile(file).stream().filter(cnabModel -> cnabModel.getErroParse() != null
                && cnabModel.getErroParse().length() > 0 ).count() == 0);
    }

    private CnabModel createCnab(String empresaNome, int natureza){
        CnabModel cnabModel = new CnabModel();
        cnabModel.setId(UUID.randomUUID());
        cnabModel.setNomeLoja(empresaNome);
        cnabModel.setNomeRepresentanteLoja("Dono Loja Teste");
        cnabModel.setCartaoTransacao("111111111111");
        cnabModel.setCpf("11111111111");
        cnabModel.setValorMovimentacao(BigDecimal.ONE);
        cnabModel.setDataOcorrencia(new Date());
        TipoTransacaoModel tipoTransacaoModel = new TipoTransacaoModel();
        tipoTransacaoModel.setId(1l);
        tipoTransacaoModel.setDescricao("Debito");
        tipoTransacaoModel.setNatureza(natureza);
        cnabModel.setTipoTransacaoModel(tipoTransacaoModel);
        return cnabModel;
    }

}
