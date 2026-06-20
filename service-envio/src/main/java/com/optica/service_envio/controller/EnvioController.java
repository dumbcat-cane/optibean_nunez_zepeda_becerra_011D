package com.optica.service_envio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_envio.model.Envio;
import com.optica.service_envio.service.EnvioService;

@RestController
@RequestMapping("/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public Envio crear(@RequestBody Envio envio){
        return envioService.guardar(envio);
    }

    @GetMapping
    public List<Envio> listar(){
        return envioService.listartodos();
    }

    @GetMapping("/{id}")
    public Envio obtener(@PathVariable Long id){
        Optional<Envio> envioOptional = Optional.ofNullable(envioService.buscarporid(id));
        if (envioOptional.isPresent()) {
            return envioOptional.get();
        }
        return null;
    }

    @GetMapping("/buscar/estado/{estado}")
    public List<Envio> buscarPorEstado(@PathVariable String estado){
        return envioService.buscarPorEstado(estado);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envioDetalles) {
        Optional<Envio> envioOptional = Optional.ofNullable(envioService.buscarporid(id));
        
        if (envioOptional.isPresent()) {
            Envio envioExistente = envioOptional.get();
            
            envioExistente.setFechaDespacho(envioDetalles.getFechaDespacho());
            envioExistente.setFechaEntrega(envioDetalles.getFechaEntrega());
            envioExistente.setDireccion(envioDetalles.getDireccion());
            envioExistente.setEstado(envioDetalles.getEstado());
            envioExistente.setIdVenta(envioDetalles.getIdVenta());
            
            return envioService.guardar(envioExistente);
        }
        
        return null;
    }

}
