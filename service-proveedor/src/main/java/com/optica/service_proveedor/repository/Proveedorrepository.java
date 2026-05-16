package com.optica.service_proveedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optica.service_proveedor.model.Proveedor;

@Repository
public interface Proveedorrepository extends JpaRepository<Proveedor,Long> {

    //buscar proveedor por su run    
    Proveedor findByRun(String run);

    Proveedor findByNombreIgnoreCase(String nombre);

}
