package com.optica.service_proveedor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "modelo que representa un proveedor del inventario")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El run es obligatorio")
    @Size(min = 9, max = 12, message = "El RUN debe tener entre 9 y 12 caracteres")
    private String run;
    @Schema(description = "debe agregar el primer nombre",example = "pepito")
    private String nombre;
    @NotBlank(message = "el telefono es obligatorio")
    private int telefono;
    @NotBlank(message = "el correo es obligatorio")
    private String correo;



}//fin
