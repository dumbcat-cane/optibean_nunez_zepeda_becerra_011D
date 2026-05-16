package com.optica.service_receta.controller;

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

import com.optica.service_receta.model.Receta;
import com.optica.service_receta.service.RecetaService;

@RestController
@RequestMapping("/recetas")

public class RecetaController {

    @Autowired
    private RecetaService recetaService;


    @PostMapping
    public Receta crear(@RequestBody Receta receta){
        return recetaService.guardarReceta(receta);
    }

    @GetMapping("/{id}")
    public Receta obtener(@PathVariable Long id){
        return recetaService.obtenerRecetaCompleto(id);
    }
    
    @GetMapping
    public List<Receta> listar(){
        return recetaService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta recetaActualizada) {
        return recetaService.actualizar(id, recetaActualizada)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
