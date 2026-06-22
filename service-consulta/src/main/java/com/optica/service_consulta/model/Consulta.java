package com.optica.service_consulta.model;

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
@Schema(description = "Modelo que representa una consulta en la optica")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha en la que se realiza la consulta medica")
    @NotNull(message = "La fecha no debe estar vacia")
    private String fecha;

    @Schema(description = "observaciones que el cliente tuvo en la consulta")
    private String diagnostico; //se dan las observaciones que el cliente tuvo en la consulta

    @Schema(description = "id unico del cliente")
    private Long clienteId;

    @Schema(description = "Datos de los clientes se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosCliente;
}
