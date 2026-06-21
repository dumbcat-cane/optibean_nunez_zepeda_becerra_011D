package com.optica.service_receta.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "modelo que representa una Receta de Consulta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Graduacion ojo derecho")
    @NotNull(message = "la graduacion no debe estar vacia")
    private String graduacionOd;
    @Schema(description = "Graduacion ojo izquierdo")
    @NotNull(message = "la graduacion no debe estar vacia")
    private String graduacionOi;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condicion_id")
    private Condicion condicion;

    private Long idConsulta;
     @Schema(description = "Datos de las consultas se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosConsulta;

}
