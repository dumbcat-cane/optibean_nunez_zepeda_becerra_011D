package com.optica.service_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.service_inventario.model.TipoMaterial;

public interface TipoMaterialRepository extends JpaRepository<TipoMaterial,Long> {

}
