package com.spring.criptografia.mapper;

import com.spring.criptografia.dto.RequisicaoDadosDTO;
import com.spring.criptografia.dto.ResponseDTO;
import com.spring.criptografia.model.DadosSensivel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DadosSensivelMapper {

   public DadosSensivel paraDadosSensivel (RequisicaoDadosDTO dto){
        DadosSensivel dadosSensivel = new DadosSensivel();
        dadosSensivel.setUsuarioDocumento(dto.usuarioDocumento());
        dadosSensivel.setCreditoCardToken(dto.creditoCardToken());
        dadosSensivel.setValor(dto.valor());
        return  dadosSensivel;
    }

   public ResponseDTO paraResposeDTO (DadosSensivel dados){
        return new ResponseDTO(dados.getId(), dados.getUsuarioDocumento(), dados.getCreditoCardToken(), dados.getValor());
    }

    public List<ResponseDTO> paraResponseList (List<DadosSensivel> lista){
        return lista.stream()
                .map(this::paraResposeDTO)
                .collect(Collectors.toList());
    }
}
