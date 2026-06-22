package com.optica.service_envio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optica.service_envio.model.Envio;
import com.optica.service_envio.model.Notificacion;
import com.optica.service_envio.repository.EnvioRepository;
import com.optica.service_envio.repository.NotificacionRepository;
import jakarta.transaction.Transactional;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository enviorepository;

    @Autowired
    private NotificacionRepository notificacionrepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Envio guardarEnvio(Envio envio){
        Envio guardado = enviorepository.save(envio);

        Envio completo = enviorepository.findById(guardado.getIdEnvio()).orElse(guardado);
        return enriquecerConVenta(completo);
    }

    public List<Envio> listartodos() {//listar listo
        List<Envio> envios = enviorepository.findAll();

        for(Envio envio : envios){
        enriquecerConVenta(envio);
    }

        return envios;
    }

    public Envio ObtenerEnvioCompleto(Long id) {
        Envio envio = enviorepository.findById(id).orElse(null);
        if(envio != null){
            return enriquecerConVenta(envio);
        }
        return null;
    }


    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionrepository.save(notificacion);
    }

    public List<Notificacion> listarNotificacion() {
        return notificacionrepository.findAll();
    }

    public void eliminar(Long id){//elimina por id una receta
        enviorepository.deleteById(id);
        
    }

    public Optional<Envio> actualizar(Long id, Envio envioDetalles) {
        return enviorepository.findById(id).map(envioExistente -> {
            envioExistente.setFechaDespacho(envioDetalles.getFechaDespacho());
            envioExistente.setFechaEntrega(envioDetalles.getFechaEntrega());
            envioExistente.setDireccion(envioDetalles.getDireccion());
            envioExistente.setEstado(envioDetalles.getEstado());
            envioExistente.setIdVenta(envioDetalles.getIdVenta());
            return enviorepository.save(envioExistente);
        });
    }

    private Envio enriquecerConVenta(Envio envio) {
        if (envio.getIdVenta() != null) {
            try {
                Object venta = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8087/ventas/" + envio.getIdVenta())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                envio.setDatosVenta(venta);
            } catch (Exception e) {
                envio.setDatosVenta("Informacion de la venta no disponible");
            }
        }
        return envio;
    }

}
