package com.optica.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_inventario.model.TipoMaterial;
import com.optica.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/tipo_material")
public class TipoMaterialController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public TipoMaterial crear(@RequestBody TipoMaterial tipoMaterial){
        return inventarioService.guardarTipoMaterial(tipoMaterial);
    }
    @GetMapping
    public List<TipoMaterial> listar(){
        return inventarioService.listarTipoMaterial();
    }

}
