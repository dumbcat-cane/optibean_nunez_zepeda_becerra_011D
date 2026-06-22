package com.optica.service_ventas.controller;

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

import com.optica.service_ventas.config.ErrorResponse;
import com.optica.service_ventas.model.Venta;
import com.optica.service_ventas.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas de los lentes")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @Operation(summary = "crea una venta")
    public Venta crear(@RequestBody Venta venta){
        return ventaService.guardarVenta(venta);
    }

    @Operation(summary = "lista toda las ventas")
    @GetMapping
    public List<Venta> listar(){
        return ventaService.listarTodos();
    }
    @Operation(summary = "obtienes una venta por su id")
    @GetMapping("/{id}")
    public Venta obtener (@PathVariable Long id){
        return ventaService.obtenerVentaCompleta(id);
    }

    @Operation(summary = "Actualizar estadística")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "venta actualizada"),
    @ApiResponse(responseCode = "404", description = "No existe la venta que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta ventaActualizada){
        return ventaService.actualizar(id, ventaActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina una venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
