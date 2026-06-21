package com.optica.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_inventario.model.TipoMaterial;
import com.optica.service_inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tipo_material")
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@Tag(name = "Tipo material", description = "Operaciones relacionadas el material de los lentes")
public class TipoMaterialController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    @Operation(summary = "crea una un tipo de material para un lente")
    public TipoMaterial crear(@RequestBody TipoMaterial tipoMaterial){
        return inventarioService.guardarTipoMaterial(tipoMaterial);
    }
    @GetMapping
    @Operation(summary = "lista todos los materiales")
    public List<TipoMaterial> listar(){
        return inventarioService.listarTipoMaterial();
    }

}
