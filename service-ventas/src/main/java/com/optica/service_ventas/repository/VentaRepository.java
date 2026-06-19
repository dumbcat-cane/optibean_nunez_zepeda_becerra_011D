package com.optica.service_ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.optica.service_ventas.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
