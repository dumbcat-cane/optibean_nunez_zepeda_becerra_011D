package com.optica.service_receta.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.service_receta.model.Condicion;

public interface CondicionRepository extends JpaRepository<Condicion, Long> {
    
}
