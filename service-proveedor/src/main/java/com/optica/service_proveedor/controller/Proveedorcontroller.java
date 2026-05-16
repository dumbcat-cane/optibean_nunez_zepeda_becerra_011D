package com.optica.service_proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_proveedor.model.Proveedor;
import com.optica.service_proveedor.service.ProveedorService;


@RestController
@RequestMapping("/proveedor")

public class Proveedorcontroller {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listar(){
        return proveedorService.listartodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id){
        return proveedorService.buscarporid(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor){
        return ResponseEntity.ok(proveedorService.guardar(proveedor));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado) {
        return proveedorService.actualizar(id, proveedorActualizado)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}//fin
