package com.spring.criptografia.repository;

import com.spring.criptografia.model.DadosSensivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosSensivelRepositoty extends JpaRepository<DadosSensivel,Long> {
}
