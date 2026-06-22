package com.optica.service_cliente.controller;

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

import com.optica.service_cliente.config.ErrorResponse;
import com.optica.service_cliente.model.Cliente;
import com.optica.service_cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "clientes", description = "Operaciones relacionadas con los clientes que compren un lente")

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "lista todos los clientes")
    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtienes un cliente por su id")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id){
        return clienteService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "crea un cliente")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @Operation(summary = "Actualizar cliente")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "cliente encontrado"),
    @ApiResponse(responseCode = "404", description = "No existe el cliente que busca", 
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PutMapping("/{id}") //esto sirve en el put de postman para modificar algo o actualizar algun dato
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return clienteService.actualizar(id, clienteActualizado)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RuntimeException("cliente no encontrado"));
    }

    @Operation(summary = "Elimina un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
    }

}
