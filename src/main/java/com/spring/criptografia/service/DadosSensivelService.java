package com.spring.criptografia.service;

import com.spring.criptografia.dto.RequisicaoDadosDTO;
import com.spring.criptografia.dto.ResponseDTO;
import com.spring.criptografia.mapper.DadosSensivelMapper;
import com.spring.criptografia.model.DadosSensivel;
import com.spring.criptografia.repository.DadosSensivelRepositoty;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DadosSensivelService {

    @Autowired
    private DadosSensivelRepositoty dadosSensivelRepositoty;

    @Autowired
    DadosSensivelMapper mapper;

    public List<ResponseDTO> BuscaTodos(){
        log.info("Listando todos os dados");
        List<DadosSensivel> dados = dadosSensivelRepositoty.findAll();
        return mapper.paraResponseList(dados);
    }
    @Cacheable(value = "dados", key = "#id")
    public ResponseEntity<ResponseDTO> buscaPorID(Long id){
        Optional<DadosSensivel> busca = dadosSensivelRepositoty.findById(id);
        if (busca.isPresent()){
            DadosSensivel dados = busca.get();
            log.info("dados encontrado");
            return ResponseEntity.ok(mapper.paraResposeDTO(dados));
        } else {
            log.info("Produto não encontrado");
            return ResponseEntity.notFound().build();
        }
    }



    public ResponseDTO Salvar (RequisicaoDadosDTO data){
        DadosSensivel dados = mapper.paraDadosSensivel(data);
        log.info("salvando dados Sensíveis");
        DadosSensivel DadosSalvo = dadosSensivelRepositoty.save(dados);
        return mapper.paraResposeDTO(dados);
    }

    @Transactional
    @CachePut(value = "dados", key = "#id")
    public ResponseEntity<ResponseDTO> AtualizarDados(Long id, RequisicaoDadosDTO requisicao){
        Optional<DadosSensivel> busca = dadosSensivelRepositoty.findById(id);
        if (busca.isPresent()) {
            DadosSensivel dadosAtualizado = busca.get();
            dadosAtualizado.setUsuarioDocumento(requisicao.usuarioDocumento());
            dadosAtualizado.setCreditoCardToken(requisicao.creditoCardToken());
            dadosAtualizado.setValor(requisicao.valor());
            dadosAtualizado.setId(id);
            log.info("dados id: " + dadosAtualizado.getId() +" atualizado com sucesso");
            return ResponseEntity.ok(mapper.paraResposeDTO(dadosAtualizado));
        } else {
            log.info("Produto: " + id +" Não encontrado na base de dados");
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @CacheEvict(value = "dados", key = "#id")
    public ResponseEntity excluirById(Long id){
        Optional<DadosSensivel> optionalDadosSensivel = dadosSensivelRepositoty.findById(id);
        if (optionalDadosSensivel.isPresent()) {
            DadosSensivel dados = optionalDadosSensivel.get();
            dadosSensivelRepositoty.deleteById(dados.getId());
            log.info("Produto: "+ id +" removido com sucesso");
            return ResponseEntity.ok().build();
        } else {
            log.info("Produto id: " + id +" Não encontrado no banco de dados");
            return ResponseEntity.notFound().build();
        }
    }
}
