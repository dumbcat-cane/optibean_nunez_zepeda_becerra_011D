package com.optica.service_receta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.service_receta.model.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{
    List<Receta> findByCondicionId(Long condicionId);

}
