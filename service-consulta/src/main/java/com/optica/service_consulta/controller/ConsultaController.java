package com.optica.service_consulta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optica.service_consulta.model.Consulta;
import com.optica.service_consulta.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

     @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public Consulta crear(
            @RequestBody Consulta consulta){

        return consultaService
                .guardarConsulta(consulta);
    }

    @GetMapping("/{id}")
    public Consulta obtener(
            @PathVariable Long id){

        return consultaService
                .obtenerConsulta(id);
    }
    @GetMapping
    public List<Consulta> listar(){

        return consultaService.listarTodas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(@PathVariable Long id, @RequestBody Consulta consultaActualizada) {
        return consultaService.actualizar(id, consultaActualizada)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
