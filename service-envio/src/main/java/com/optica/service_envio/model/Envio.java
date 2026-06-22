package com.optica.service_envio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un Envio o Despacho de productos de la optica")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long idEnvio;

    @Schema(description = "Fecha programada para que el producto salga a despacho")
    @NotNull(message = "La fecha de despacho no debe estar vacia")
    @Column(name = "fecha_despacho")
    private String fechaDespacho;

    @Schema(description = "Fecha estimada o real en la que se entrega el producto al cliente")
    @NotNull(message = "La fecha de entrega no debe estar vacia")
    @Column(name = "fecha_entrega")
    private String fechaEntrega;

    @Schema(description = "Direccion de destino donde se entregara el paquete")
    @NotNull(message = "La direccion no debe estar vacia")
    @Column(name = "direccion")
    private String direccion;

    @Schema(description = "Estado actual del envio (ej: PENDIENTE, EN_TRANSITO, ENTREGADO)")
    @NotNull(message = "El estado no debe estar vacio")
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_notificacion")
    private Notificacion notificacion;

    @Schema(description = "ID unico de la venta que origino este envio")
    @NotNull(message = "El id de la venta no debe estar vacio")
    private Long idVenta;

    @Schema(description = "Datos detallados de la venta asociados en tiempo de ejecucion mediante WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosVenta;
}
