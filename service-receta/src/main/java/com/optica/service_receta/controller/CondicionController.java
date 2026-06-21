package com.optica.service_receta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_receta.model.Condicion;
import com.optica.service_receta.service.RecetaService;
@CrossOrigin(origins = "*")//swagger puede llamar desde cualquier puerto
@RestController
@RequestMapping("/condiciones")
public class CondicionController {
    @Autowired
    private RecetaService recetaservice;

    @PostMapping
    public Condicion crear(@RequestBody Condicion condicion){
        return recetaservice.guardarCondicion(condicion);
    }

    @GetMapping
    public List<Condicion> listar(){
        return recetaservice.listarCondicion();
    }


}//fin
