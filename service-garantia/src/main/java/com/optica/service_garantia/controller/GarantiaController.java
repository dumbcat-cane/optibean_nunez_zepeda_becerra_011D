package com.optica.service_garantia.controller;

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

import com.optica.service_garantia.model.Garantia;
import com.optica.service_garantia.service.GarantiaService;

@RestController
@RequestMapping("/garantias")

public class GarantiaController {
    @Autowired
    private GarantiaService garantiaService;


    @PostMapping
    public Garantia crear(@RequestBody Garantia garantia){
        return garantiaService.guardGarantia(garantia);
    }

    @GetMapping("/{id}")
    public Garantia obtener(@PathVariable Long id){
        return garantiaService.obtenerGarantiaCompleta(id);
        
    }

    @GetMapping
    public List<Garantia> listar(){
        return garantiaService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garantia> actualizar(@PathVariable Long id, @RequestBody Garantia garantiaActualizada){
        return garantiaService.actualizar(id, garantiaActualizada)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        garantiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }










}
