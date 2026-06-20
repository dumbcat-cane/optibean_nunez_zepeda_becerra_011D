package com.optica.service_garantia.model;

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

public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "el motivo no debe estar vacio")
    private String motivo;
    @Schema(description = "Estado del lente")
    @NotNull(message = "el estado no debe estar vacio")
    private String estado;
    @NotNull(message = "la fecha de la solicitud no debe estar vacia")
    private String fechasolicitud;
    @Schema(description = "Detalle de la acción tomada: reparación, reemplazo o devolución")
    private String resolucion;

    private Long idventa;
    @Schema(description = "Datos de la venta se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient 
    private Object datosVenta;
    

}
