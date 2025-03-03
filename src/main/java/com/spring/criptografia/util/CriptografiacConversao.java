package com.spring.criptografia.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class CriptografiacConversao implements AttributeConverter <String,String> {
    @Override
    public String convertToDatabaseColumn(String atributos) {
        return CriptografiaUtil.encripitar(atributos);
    }

    @Override
    public String convertToEntityAttribute(String dadosBD) {
        return CriptografiaUtil.descriptografar(dadosBD);
    }
}
