package com.optica.service_envio.service;

import java.util.List;
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

    public List<Envio> listartodos() {
        return enviorepository.findAll();
    }

    public Envio buscarporid(Long id) {
        return enviorepository.findById(id).orElse(null);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return enviorepository.findByEstadoContainingIgnoreCase(estado);
    }

    @Transactional
    public Envio guardar(Envio envio) {
        return enviorepository.save(envio);
    }

    @Transactional
    public Envio actualizar(Long id, Envio envioDetalles) {
        Envio envioExistente = enviorepository.findById(id).orElse(null);
        if (envioExistente != null) {
            envioExistente.setFechaDespacho(envioDetalles.getFechaDespacho());
            envioExistente.setFechaEntrega(envioDetalles.getFechaEntrega());
            envioExistente.setDireccion(envioDetalles.getDireccion());
            envioExistente.setEstado(envioDetalles.getEstado());
            envioExistente.setIdVenta(envioDetalles.getIdVenta());
            return enviorepository.save(envioExistente);
        }
        return null;
    }

    @Transactional
    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionrepository.save(notificacion);
    }

    public List<Notificacion> listarNotificacion() {
        return notificacionrepository.findAll();
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
