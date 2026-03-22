package com.spring.criptografia.service;

import com.spring.criptografia.dto.RequisicaoDadosDTO;
import com.spring.criptografia.dto.ResponseDTO;
import com.spring.criptografia.mapper.DadosSensivelMapper;
import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.repository.DadosSensivelRepositoty;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DadosSensivelServiceTest {

    @Mock
    private DadosSensivelRepositoty dadosSensivelRepositoty;

    @Mock
    private DadosSensivelMapper mapper;

    @InjectMocks
    private DadosSensivelService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaTodos_DeveRetornarListaDeResponseDTO() {
        List<DadosSensivel> dados = List.of(new DadosSensivel(1L, "123456", "token123",  100));
        List<ResponseDTO> dto = List.of(new ResponseDTO(1L, "123456", "token123", 100));

        when(dadosSensivelRepositoty.findAll()).thenReturn(dados);
        when(mapper.paraResponseList(dados)).thenReturn(dto);

        List<ResponseDTO> resultado = service.BuscaTodos();

        assertEquals(dto, resultado);
        verify(dadosSensivelRepositoty).findAll();
        verify(mapper).paraResponseList(dados);
    }

    @Test
    void buscaPorID_DadoExiste_DeveRetornar200() {
        Long id = 1L;
        DadosSensivel dados = new DadosSensivel(id, "123456", "token123", 100);
        ResponseDTO dto = new ResponseDTO(id, "123456", "token123", 100);

        when(dadosSensivelRepositoty.findById(id)).thenReturn(Optional.of(dados));
        when(mapper.paraResposeDTO(dados)).thenReturn(dto);

        ResponseEntity<ResponseDTO> response = service.buscaPorID(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void buscaPorID_DadoNaoExiste_DeveRetornar404() {
        when(dadosSensivelRepositoty.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<ResponseDTO> response = service.buscaPorID(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void salvar_DeveRetornarResponseDTO() {
        RequisicaoDadosDTO requisicao = new RequisicaoDadosDTO("123456", "token123", 100);
        DadosSensivel entidade = new DadosSensivel(null, "123456", "token123", 100);
        DadosSensivel salvo = new DadosSensivel(1L, "123456", "token123", 100);
        ResponseDTO resposta = new ResponseDTO(1L, "123456", "token123", 100);

        when(mapper.paraDadosSensivel(requisicao)).thenReturn(entidade);
        when(dadosSensivelRepositoty.save(entidade)).thenReturn(salvo);
        when(mapper.paraResposeDTO(entidade)).thenReturn(resposta);

        ResponseDTO resultado = service.Salvar(requisicao);

        assertEquals(resposta, resultado);
    }

    @Test
    void atualizarDados_Existe_DeveAtualizarEretornar200() {
        Long id = 1L;
        RequisicaoDadosDTO requisicao = new RequisicaoDadosDTO("654321", "token999", 200);
        DadosSensivel existente = new DadosSensivel(id, "123456", "token123", 100);
        ResponseDTO respostaEsperada = new ResponseDTO(id, "654321", "token999", 200);

        when(dadosSensivelRepositoty.findById(id)).thenReturn(Optional.of(existente));
        when(mapper.paraResposeDTO(existente)).thenReturn(respostaEsperada);

        ResponseEntity<ResponseDTO> response = service.AtualizarDados(id, requisicao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(respostaEsperada, response.getBody());

        assertEquals("654321", existente.getUsuarioDocumento());
        assertEquals("token999", existente.getCreditoCardToken());
        assertEquals(200, existente.getValor());
    }

    @Test
    void atualizarDados_NaoExiste_DeveRetornar404() {
        when(dadosSensivelRepositoty.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<ResponseDTO> response = service.AtualizarDados(999L,
                new RequisicaoDadosDTO("x", "y", 0));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void excluirById_Existe_DeveExcluirERetornar200() {
        Long id = 1L;
        DadosSensivel dados = new DadosSensivel(id, "123", "tok", 10);

        when(dadosSensivelRepositoty.findById(id)).thenReturn(Optional.of(dados));

        ResponseEntity response = service.excluirById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dadosSensivelRepositoty).deleteById(id);
    }

    @Test
    void excluirById_NaoExiste_DeveRetornar404() {
        when(dadosSensivelRepositoty.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity response = service.excluirById(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dadosSensivelRepositoty, never()).deleteById(any());
    }

}