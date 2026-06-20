package com.optica.service_envio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.optica.service_envio.model.Envio;
import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long>{

    List<Envio> findByEstadoContainingIgnoreCase(String estado);

}
