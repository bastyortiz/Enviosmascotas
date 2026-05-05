package com.duoc.enviosmascotas.repository;

import com.duoc.enviosmascotas.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    boolean existsByNumeroSeguimiento(String numeroSeguimiento);
}
