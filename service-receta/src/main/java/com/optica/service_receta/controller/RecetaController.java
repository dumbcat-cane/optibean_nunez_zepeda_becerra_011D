package com.optica.service_receta.controller;

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

import com.optica.service_receta.config.ErrorResponse;
import com.optica.service_receta.model.Receta;
import com.optica.service_receta.service.RecetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/recetas")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Receta", description = "Operaciones relacionadas con las Recetas de Consulta")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;
    
    @Operation(summary = "crea una receta")
    @PostMapping
    public Receta crear(@RequestBody Receta receta){
        return recetaService.guardarReceta(receta);
    }

    @Operation(summary = "obtiener una receta por su id")
    @GetMapping("/{id}")
    public Receta obtener(@PathVariable Long id){
        return recetaService.obtenerRecetaCompleto(id);
    }
    @Operation(summary = "lista de todas las recetas")
    @GetMapping
    public List<Receta> listar(){
        return recetaService.listarTodos();
    }

    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "receta encontrada"),
    @ApiResponse(responseCode = "404", description = "No existe la receta que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @Operation(summary = "actualizar una receta")
    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta recetaActualizada) {
        return recetaService.actualizar(id, recetaActualizada)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "eliminar una receta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
