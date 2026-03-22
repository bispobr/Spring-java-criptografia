package com.spring.criptografia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.criptografia.dto.RequisicaoDadosDTO;
import com.spring.criptografia.dto.ResponseDTO;
import com.spring.criptografia.service.DadosSensivelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DadosSensivelControllerTest {

    @Mock
    private DadosSensivelService dadosSensivelService;


    @InjectMocks
    private DadosSensivelController dadosSensivelController;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(dadosSensivelController).build();
    }

    @Test
    void listarTodos_DeveRetornar200ComLista() throws Exception {
        List<ResponseDTO> lista = List.of(new ResponseDTO(1L, "123", "tok", 10));
        when(dadosSensivelService.BuscaTodos()).thenReturn(lista);

        mockMvc.perform(get("/dados-sensivel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void listarPorId_Encontrado_DeveRetornar200() throws Exception {
        Long id = 1L;
        ResponseDTO dto = new ResponseDTO(id, "123", "tok", 10);
        when(dadosSensivelService.buscaPorID(id)).thenReturn(ResponseEntity.ok(dto));

        mockMvc.perform(get("/dados-sensivel/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void listarPorId_NaoEncontrado_DeveRetornar404() throws Exception {
        when(dadosSensivelService.buscaPorID(99L)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get("/dados-sensivel/{id}", 99L))
                .andExpect(status().isNotFound());
    }

    @Test
    void criar_DeveRetornar201ComDadosSalvos() throws Exception {
        RequisicaoDadosDTO requisicao = new RequisicaoDadosDTO("123", "tok", 10);
        ResponseDTO resposta = new ResponseDTO(1L, "123", "tok", 10);

        when(dadosSensivelService.Salvar(any())).thenReturn(resposta);

        mockMvc.perform(post("/dados-sensivel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requisicao)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void atualizarDados_Encontrado_DeveRetornar200() throws Exception {
        Long id = 1L;
        RequisicaoDadosDTO dto = new RequisicaoDadosDTO("abc", "tok", 10);
        ResponseDTO resposta = new ResponseDTO(id, "abc", "tok", 10);

        when(dadosSensivelService.AtualizarDados(id, dto)).thenReturn(ResponseEntity.ok(resposta));

        mockMvc.perform(put("/dados-sensivel/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioDocumento").value("abc"));
    }

    @Test
    void atualizarDados_NaoEncontrado_DeveRetornar404() throws Exception {
        RequisicaoDadosDTO dto = new RequisicaoDadosDTO("abc", "tok", 10);

        when(dadosSensivelService.AtualizarDados(99L, dto))
                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        mockMvc.perform(put("/dados-sensivel/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_Encontrado_DeveRetornar200() throws Exception {
        when(dadosSensivelService.excluirById(1L)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/dados-sensivel/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_NaoEncontrado_DeveRetornar404() throws Exception {
        when(dadosSensivelService.excluirById(99L)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(delete("/dados-sensivel/99"))
                .andExpect(status().isNotFound());
    }


}