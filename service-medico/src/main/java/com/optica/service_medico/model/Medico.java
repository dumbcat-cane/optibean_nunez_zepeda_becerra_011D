package com.optica.service_medico.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "Medico")
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "modelo que representa a un medico del inventario consulta")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El run es obligatorio")
    @Size(min = 9, max = 12, message = "El RUN debe tener entre 9 y 12 caracteres")
    private String run;
    @Schema(description = "primer nombre del medico")
    private String nombre;
    @Size(min = 9, max = 10, message = "El telefono debe tener 9 numeros")
    private int telefono;
    @NotBlank(message = "el correo es obligatorio")
    private String correo;
    @Schema(description = "primer nombre del medico")
    private String especialidad;

    private Long idConsulta;
    @Schema(description = "Datos de consulta se cargan en tiempo de ejecucion via webclient", accessMode = Schema.AccessMode.READ_ONLY) 
    @Transient
    private Object datosConsulta;

}
