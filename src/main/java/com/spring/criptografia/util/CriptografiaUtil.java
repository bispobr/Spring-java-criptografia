package com.spring.criptografia.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class CriptografiaUtil {

    private static final String ALGORITIMO = "AES";
    private static final SecretKey CHAVE_SECRETA = geradorChave();

    private static SecretKey geradorChave(){
        try {
            KeyGenerator chavegerador = KeyGenerator.getInstance(ALGORITIMO);
            chavegerador.init(128);
            return chavegerador.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("falha ao gerar chave encriptada",e);
        }
    }

    public static String encripitar(final String dados){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITIMO);
            cipher.init(Cipher.ENCRYPT_MODE,CHAVE_SECRETA);
            byte[] bytesencriptados = cipher.doFinal(dados.getBytes());
            return Base64.getEncoder().encodeToString(bytesencriptados);

        } catch (Exception e) {
            throw new RuntimeException("falha ao encriptar os dados",e);
        }
    }

    public  static String descriptografar (final String dadosEncriptador){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITIMO);
            cipher.init(Cipher.DECRYPT_MODE,CHAVE_SECRETA);
            byte[] decodificarBytes = Base64.getDecoder().decode(dadosEncriptador);
            byte[] bytesDecodificados = cipher.doFinal(decodificarBytes);
            return  new String(bytesDecodificados);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao descriptografar dados",e);
        }

    }

}
