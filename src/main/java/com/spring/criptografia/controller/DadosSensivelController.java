package com.spring.criptografia.controller;

import com.spring.criptografia.dto.RequisicaoDadosDTO;
import com.spring.criptografia.dto.ResponseDTO;
import com.spring.criptografia.service.DadosSensivelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<List<ResponseDTO>> listarTodos(){
        log.info("Requisição para listar dados recebida");
        return ResponseEntity.ok().body(dadosSensivelService.BuscaTodos());
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsável por listar dados por id")
    @ApiResponse (responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<ResponseDTO> listarById(@PathVariable("id")Long id) {
        log.info("solicitação de busca por id recebida;");
        return dadosSensivelService.buscaPorID(id);
    }

    @PostMapping
    @Operation(description = "Endpoint responsável por salvar dados")
    @ApiResponse(responseCode = "201", description = "dados salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<ResponseDTO> criar(@RequestBody @Valid RequisicaoDadosDTO requisicao){
        log.info("Requisição para salvar dados sensíveis recebida");
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosSensivelService.Salvar(requisicao)) ;
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint responsável por atualizar dados")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição, dados enviados não atendem os requisitos")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<ResponseDTO> atualizarDados(@PathVariable("id")Long id , @RequestBody @Valid RequisicaoDadosDTO data) {
        log.info("Solicitação de Atualização de dados recebida");
        return dadosSensivelService.AtualizarDados(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsável por remover dados")
    @ApiResponse(responseCode = "200", description = "dados removidos com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity delete(@PathVariable Long id){
        log.info("Requisição para remover dados recebida");
        return dadosSensivelService.excluirById(id);
    }
}
