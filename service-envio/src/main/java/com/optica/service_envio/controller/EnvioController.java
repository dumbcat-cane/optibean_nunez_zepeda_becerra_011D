package com.optica.service_envio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_envio.config.ErrorResponse;
import com.optica.service_envio.model.Envio;
import com.optica.service_envio.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/envio")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Envio", description = "Operaciones relacionadas con los Envios de la optica")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Operation(summary = "crea un envio")
    @PostMapping
    public Envio crear(@RequestBody Envio envio){
        return envioService.guardar(envio);
    }

    @Operation(summary = "lista de todos los envios")
    @GetMapping
    public List<Envio> listar(){
        return envioService.listartodos();
    }

    @Operation(summary = "obtiener un envio por su id")
    @GetMapping("/{id}")
    public Envio obtener(@PathVariable Long id){
        Optional<Envio> envioOptional = Optional.ofNullable(envioService.buscarporid(id));
        if (envioOptional.isPresent()) {
            return envioOptional.get();
        }
        return null;
    }

    @Operation(
        summary = "Buscar envios por estado", 
        description = "Retorna una lista de todos los envios que coincidan con el estado proporcionado (ej: 'PENDIENTE', 'DESPACHADO')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de envios filtrada obtenida con exito"),
        @ApiResponse(responseCode = "400", description = "El estado proporcionado no es valido")
    })

    @GetMapping("/buscar/estado/{estado}")
    public List<Envio> buscarPorEstado(@PathVariable String estado){
        return envioService.buscarPorEstado(estado);
    }

    @Operation(summary = "Actualizar un envio", description = "Modifica los datos de un envio existente buscando por su ID unico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envio actualizado con exito"),
        @ApiResponse(responseCode = "404", description = "No existe el envio que busca",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio envioDetalles) {
        return envioService.actualizar(id, envioDetalles)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "eliminar un envio")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
