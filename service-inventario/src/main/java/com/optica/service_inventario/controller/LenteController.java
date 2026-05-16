package com.optica.service_inventario.controller;

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

import com.optica.service_inventario.model.Lente;
import com.optica.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/lentes")
public class LenteController {
    @Autowired
    private InventarioService inventarioService;
    @PostMapping
    public Lente crear(@RequestBody Lente lente){
        return inventarioService.guardarLente(lente);
    }
    @GetMapping("/{id}")
    public Lente verLente(@PathVariable Long id){
        return inventarioService.obtenerLenteCompleto(id);
    }
    @GetMapping
    public List<Lente> listar(){
        return inventarioService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lente> actualizar(@PathVariable Long id, @RequestBody Lente inventarioActualizado) {
        return inventarioService.actualizar(id, inventarioActualizado)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") //borra los datos
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
