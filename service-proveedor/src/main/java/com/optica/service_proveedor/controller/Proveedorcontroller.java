package com.optica.service_proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "proveedor", description = "Operaciones relacionadas el inventario de proveedor")

@RestController
@RequestMapping("/proveedor")

public class Proveedorcontroller {

    @Autowired
    private ProveedorService proveedorService;
    @Operation(summary = "crea un proveedor")
    @GetMapping
    public List<Proveedor> listar(){
        return proveedorService.listartodos();
    }
    @Operation(summary = "obtienes un proveedor por su id")
    @GetMapping("{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id){
        return proveedorService.buscarporid(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
    }

    @Operation(summary = "crea un proveedor")
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor){
        return ResponseEntity.ok(proveedorService.guardar(proveedor));

    }
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "proveedor encontrado"),
    @ApiResponse(responseCode = "404", description = "No existe el proveedor que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })


    @Operation(summary = "actualiza los datos de un proveedor")
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado) {
        return proveedorService.actualizar(id, proveedorActualizado)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RuntimeException("proveedor no encontrados"));
    }
    @Operation(summary = "elimina a un proveedor")   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}//fin
