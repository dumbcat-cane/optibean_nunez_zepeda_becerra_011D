package com.optica.service_garantia.controller;

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

import com.optica.service_garantia.config.ErrorResponse;
import com.optica.service_garantia.model.Garantia;
import com.optica.service_garantia.service.GarantiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/garantias")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Garantia", description = "Garantia del lente vendido")

public class GarantiaController {
    @Autowired
    private GarantiaService garantiaService;

    @Operation(summary = "crear ua garantia")
    @PostMapping
    public Garantia crear(@RequestBody Garantia garantia){
        return garantiaService.guardGarantia(garantia);
    }
    @Operation(summary = "obtiener una garantia por su id")
    @GetMapping("/{id}")
    public Garantia obtener(@PathVariable Long id){
        return garantiaService.obtenerGarantiaCompleta(id);
        
    }
    @Operation(summary = "lista de todas las garantias")
    @GetMapping
    public List<Garantia> listar(){
        return garantiaService.listarTodos();
    }

    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "garantia encontrada"),
    @ApiResponse(responseCode = "404", description = "No existe la garantia que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    
    @Operation(summary = "actualizar una garantia")
    @PutMapping("/{id}")
    public ResponseEntity<Garantia> actualizar(@PathVariable Long id, @RequestBody Garantia garantiaActualizada){
        return garantiaService.actualizar(id, garantiaActualizada)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RuntimeException("garantia no encontrada"));
    }
    
    @Operation(summary = "eliminar una garantia")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        garantiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }










}
