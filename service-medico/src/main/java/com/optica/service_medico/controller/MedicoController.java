package com.optica.service_medico.controller;

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

import com.optica.service_medico.config.ErrorResponse;
import com.optica.service_medico.model.Medico;
import com.optica.service_medico.service.MedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Medico", description = "Operaciones relacionadas con la gestion de medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;
    @Operation(summary = "crea una medico")
    @PostMapping
    public Medico crear(@RequestBody Medico medico){
        return medicoService.guardarMedico(medico);
    }
    @Operation(summary = "lista de los medicos disponibles")
    @GetMapping
    public List<Medico> listar(){
        return medicoService.listarTodos();
    }


    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "estadistica encontrada"),
    @ApiResponse(responseCode = "404", description = "No existe la estadistica que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @Operation(summary = "obtienes un medico por su id")
    @GetMapping("/{id}")
    public Medico obtener(@PathVariable Long id){
        return medicoService.obtenerMedicoCompleto(id);
    }
    @Operation(summary = "actualiza datos de un medico")
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Long id, @RequestBody Medico medico){

        return medicoService.actualizar(id, medico)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("medico no encontrado"));
    }
    @Operation(summary = "elimina un medico por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        medicoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
