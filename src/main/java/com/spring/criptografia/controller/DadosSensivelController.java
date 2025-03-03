package com.spring.criptografia.controller;

import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.service.DadosSensivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/dados-sensivel")
public class DadosSensivelController {

    @Autowired
    private DadosSensivelService dadosSensivelService;

    @GetMapping
    public List<DadosSensivel> listarTodos(){
        return dadosSensivelService.BuscaTodos();
    }

    @PostMapping
     public  DadosSensivel criar(@RequestBody DadosSensivel data){
        return dadosSensivelService.Salvar(data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        dadosSensivelService.excluirById(id);
    }
}
