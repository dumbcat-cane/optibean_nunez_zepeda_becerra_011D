package com.optica.service_medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.service_medico.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
