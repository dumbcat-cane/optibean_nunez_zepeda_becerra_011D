package com.optica.service_consulta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optica.service_consulta.config.ErrorResponse;
import com.optica.service_consulta.model.Consulta;
import com.optica.service_consulta.service.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/consultas")
@CrossOrigin(origins = "*")
@Tag(name = "Consultas", description = "Operaciones relacionadas con las consultas de la optica")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Operation(summary = "crea una consulta")
    public Consulta crear(
            @RequestBody Consulta consulta){

        return consultaService
                .guardarConsulta(consulta);
    }

    @Operation(summary = "obtienes una consulta por su id")
    @GetMapping("/{id}")
    public Consulta obtener(
            @PathVariable Long id){

        return consultaService
                .obtenerConsulta(id);
    }

    @Operation(summary = "lista toda las consultas")
    @GetMapping
    public List<Consulta> listar(){

        return consultaService.listarTodas();
    }

    @Operation(summary = "Actualizar consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "consulta encontrada"),
        @ApiResponse(
            responseCode = "404", 
            description = "No existe la consulta que busca",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(@PathVariable Long id, @RequestBody Consulta consultaActualizada) {
        return consultaService.actualizar(id, consultaActualizada)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina una consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
