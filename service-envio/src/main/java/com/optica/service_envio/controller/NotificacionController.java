package com.optica.service_envio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.service_envio.model.Notificacion;
import com.optica.service_envio.repository.NotificacionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @PostMapping
    public Notificacion crear(@RequestBody Notificacion notificacion){
        return notificacionRepository.save(notificacion);
    }

    @GetMapping
    public List<Notificacion> listar(){
        return notificacionRepository.findAll();
    }
}
