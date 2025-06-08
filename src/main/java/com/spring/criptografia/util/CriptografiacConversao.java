package com.spring.criptografia.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Convert
public class CriptografiacConversao implements AttributeConverter <String,String> {
    @Override
    public String convertToDatabaseColumn(String atributos) {
        log.info("Encriptando dados");
        return CriptografiaUtil.encripitar(atributos);
    }

    @Override
    public String convertToEntityAttribute(String dadosBD) {
        log.info("Decrepitando dados");
        return CriptografiaUtil.descriptografar(dadosBD);
    }
}
