package com.spring.criptografia.service;

import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.repository.DadosSensivelRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DadosSensivelService {

    @Autowired
    private DadosSensivelRepositoty dadosSensivelRepositoty;

    public List<DadosSensivel> BuscaTodos(){
        log.info("Listando todos os dados");
        return dadosSensivelRepositoty.findAll();
    }

    public DadosSensivel Salvar (DadosSensivel data){
        log.info("salvando dados Sensíveis");
        return dadosSensivelRepositoty.save(data);
    }

    public void excluirById(Long id){
        log.info("removendo dados Sensíveis");
        dadosSensivelRepositoty.deleteById(id);
    }
}
