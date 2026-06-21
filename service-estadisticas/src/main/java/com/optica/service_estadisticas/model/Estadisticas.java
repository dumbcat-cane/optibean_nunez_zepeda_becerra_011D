package com.optica.service_estadisticas.model;

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



@Schema(description = "modelo que representa una estadisitica del inventario de lentes")
public class Estadisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "el nombre del reporte no debe estar vacio")
    private String nombreReporte;
    
    @NotNull(message = "la observacion no debe estar vacia")
    private String observacion;

    private Long idLente;
    @Schema(description = "Datos de los lentes se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datoslente;


}
