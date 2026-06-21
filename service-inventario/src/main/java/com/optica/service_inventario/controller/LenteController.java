package com.optica.service_inventario.controller;

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

import com.optica.service_inventario.config.ErrorResponse;
import com.optica.service_inventario.model.Lente;
import com.optica.service_inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/lentes")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Lentes", description = "Operaciones relacionadas el inventario de lentes")
public class LenteController {
    @Autowired
    private InventarioService inventarioService;
    @PostMapping
    @Operation(summary = "crea un lente")
    public Lente crear(@RequestBody Lente lente){
        return inventarioService.guardarLente(lente);
    }
    @Operation(summary = "obtienes un lente por su id")
    @GetMapping("/{id}")
    public Lente verLente(@PathVariable Long id){
        return inventarioService.obtenerLenteCompleto(id);
    }
    @Operation(summary = "lista todo los lentes")
    @GetMapping
    public List<Lente> listar(){
        return inventarioService.listarTodos();
    }

    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "estadistica encontrada"),
    @ApiResponse(responseCode = "404", description = "No existe la estadistica que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })


    @Operation(summary = "actualiza los datos de un lente")
    @PutMapping("/{id}")
    public ResponseEntity<Lente> actualizar(@PathVariable Long id, @RequestBody Lente inventarioActualizado) {
        return inventarioService.actualizar(id, inventarioActualizado)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RuntimeException("estadistica no encontrada"));
    }
    @Operation(summary = "elimina un lente")
    @DeleteMapping("/{id}") //borra los datos
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
