package com.optica.service_cliente.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "modelo que representa a un cliente")
public class Cliente {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El run es obligatorio")
    @Size(min = 9, max = 12, message = "El RUN debe tener entre 9 y 12 caracteres")
    private String run;

    @Schema(description = "ingrese ambos nombres")
    private String nombres;

    @Schema(description = "ingrese ambos apellidos")
    private String apellidos;
}