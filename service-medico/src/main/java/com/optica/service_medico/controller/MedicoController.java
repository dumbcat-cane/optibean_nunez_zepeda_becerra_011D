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

import com.optica.service_medico.model.Medico;
import com.optica.service_medico.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public Medico crear(@RequestBody Medico medico){
        return medicoService.guardarMedico(medico);
    }

    @GetMapping
    public List<Medico> listar(){
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Medico obtener(@PathVariable Long id){
        return medicoService.obtenerMedicoCompleto(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Long id, @RequestBody Medico medico){

        return medicoService.actualizar(id, medico)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        medicoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
