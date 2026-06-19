package com.optica.service_estadisticas.controller;

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

import com.optica.service_estadisticas.model.Estadisticas;
import com.optica.service_estadisticas.service.EstadisticasService;

@RestController
@RequestMapping("/estadistica")

public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;


    @PostMapping
    public Estadisticas crear(@RequestBody Estadisticas estadisticas){
        return estadisticasService.guardarEstadistica(estadisticas);
    }

    @GetMapping("/{id}")
    public Estadisticas obtener(@PathVariable Long id){
        return estadisticasService.obtenerEstadisticaCompleta(id);

    }


    @GetMapping
    public List<Estadisticas> listar(){
        return estadisticasService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estadisticas> actualizar(@PathVariable Long id, @RequestBody Estadisticas estadisticasactualizada){
        return estadisticasService.actualizar(id, estadisticasactualizada)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estadisticasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
