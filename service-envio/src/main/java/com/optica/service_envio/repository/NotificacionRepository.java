package com.optica.service_envio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.optica.service_envio.model.Notificacion;
import java.util.List;
public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{

    List<Notificacion> findByMensajeContainingIgnoreCase(String mensaje);

}
