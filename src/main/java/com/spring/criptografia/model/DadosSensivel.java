package com.spring.criptografia.model;

import com.spring.criptografia.util.CriptografiacConversao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "Tb_dados_sensivel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosSensivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CriptografiacConversao.class)
    @Column(name = "usuario_documento",nullable = false)
    private String usuarioDocumento;

    @Convert(converter = CriptografiacConversao.class)
    @Column(name = "credito_card_token",nullable = false)
    private String creditoCardToken;

    @Column(name = "valor_credito",nullable = false)
    private long valor;

}
