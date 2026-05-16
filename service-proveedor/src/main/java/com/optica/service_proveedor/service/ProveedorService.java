package com.optica.service_proveedor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.service_proveedor.model.Proveedor;
import com.optica.service_proveedor.repository.Proveedorrepository;


@Service
public class ProveedorService {

    @Autowired
    private Proveedorrepository proveedorrepository;

    public List<Proveedor> listartodos(){
        return proveedorrepository.findAll();
        
    }

    public Optional<Proveedor> buscarporid(Long id){
        return proveedorrepository.findById(id);

    }

    public Proveedor guardar(Proveedor proveedor){
        return proveedorrepository.save(proveedor);
    }

    public Optional<Proveedor> actualizar(Long id, Proveedor proveedorActualizado) {
    return proveedorrepository.findById(id)
        .map(proveedor -> {
            proveedor.setRun(proveedorActualizado.getRun());
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
            proveedor.setCorreo(proveedorActualizado.getCorreo());
            return proveedorrepository.save(proveedor);
        });
    }

    public void eliminar(Long id){
        proveedorrepository.deleteById(id);
    }



}//fin