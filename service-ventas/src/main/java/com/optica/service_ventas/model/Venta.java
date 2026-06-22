package com.optica.service_ventas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa una venta de la optica")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha en la que se realizo la venta")
    @NotNull(message = "La fecha de venta no debe estar vacia")
    private String fechaVenta;

    @Schema(description = "Monto total economico de la venta")
    @NotNull(message = "El monto no debe estar vacio")
    private int monto;

    @Schema(description = "Metodo utilizado para el pago (ej: EFECTIVO, TARJETA)")
    @NotNull(message = "El metodo de pago no debe estar vacio")
    private String metodoPago;

    @Schema(description = "ID unico del cliente asociado a la venta")
    @NotNull(message = "El id del cliente no debe estar vacio")
    private Long idCliente;

    @Schema(description = "ID unico del lente vendido")
    @NotNull(message = "El id del lente no debe estar vacio")
    private Long idLente;

    @Schema(description = "Datos del cliente se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosCliente;

    @Schema(description = "Datos de los lentes se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosLente;

}
