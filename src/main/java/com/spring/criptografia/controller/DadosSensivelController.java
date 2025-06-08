package com.spring.criptografia.controller;

import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.service.DadosSensivelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/dados-sensivel")
public class DadosSensivelController {

    @Autowired
    private DadosSensivelService dadosSensivelService;

    @GetMapping
    @Operation(description = "Endpoint responsável por listar todos os dados")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public List<DadosSensivel> listarTodos(){
        log.info("Requisição para listar dados recebida");
        return dadosSensivelService.BuscaTodos();
    }

    @PostMapping
    @Operation(description = "Endpoint responsável por salvar dados")
    @ApiResponse(responseCode = "200", description = "dados salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno")
     public  DadosSensivel criar(@RequestBody DadosSensivel data){
        log.info("Requisição para salvar dados sensíveis recebida");
        return dadosSensivelService.Salvar(data);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsável por remover dados")
    @ApiResponse(responseCode = "200", description = "dados removidos com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public void delete(@PathVariable Long id){
        log.info("Requisição para remover dados recebida");
        dadosSensivelService.excluirById(id);
    }
}
