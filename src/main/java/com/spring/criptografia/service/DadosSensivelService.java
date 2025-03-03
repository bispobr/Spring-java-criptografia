package com.spring.criptografia.service;

import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.repository.DadosSensivelRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosSensivelService {

    @Autowired
    private DadosSensivelRepositoty dadosSensivelRepositoty;

    public List<DadosSensivel> BuscaTodos(){
        return dadosSensivelRepositoty.findAll();
    }

    public DadosSensivel Salvar (DadosSensivel data){
        return dadosSensivelRepositoty.save(data);
    }

    public void excluirById(Long id){
        dadosSensivelRepositoty.deleteById(id);
    }
}
