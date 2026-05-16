package com.optica.service_consulta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.service_consulta.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
