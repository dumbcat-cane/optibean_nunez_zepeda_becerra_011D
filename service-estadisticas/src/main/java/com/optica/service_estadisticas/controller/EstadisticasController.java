package com.optica.service_estadisticas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_estadisticas.config.ErrorResponse;
import com.optica.service_estadisticas.model.Estadisticas;
import com.optica.service_estadisticas.service.EstadisticasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/estadistica")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Estadisticas", description = "Operaciones relacionadas con las estadisticas de lentes")
public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;


    @PostMapping
    @Operation(summary = "crea una estadistica")
    public Estadisticas crear(@RequestBody Estadisticas estadisticas){
        return estadisticasService.guardarEstadistica(estadisticas);
    }
    @Operation(summary = "obtienes una estadistica por su id")
    @GetMapping("/{id}")
    public Estadisticas obtener(@PathVariable Long id){
        return estadisticasService.obtenerEstadisticaCompleta(id);

    }


    @GetMapping
    public List<Estadisticas> listar(){
        return estadisticasService.listarTodos();
    }

    @Operation(summary = "Actualizar estadística")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "estadistica encontrada"),
    @ApiResponse(responseCode = "404", description = "No existe la estadistica que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PutMapping("/{id}")
    public ResponseEntity<Estadisticas> actualizar(@PathVariable Long id, @RequestBody Estadisticas estadisticasactualizada){
        return estadisticasService.actualizar(id, estadisticasactualizada)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RuntimeException("estadistica no encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estadisticasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
