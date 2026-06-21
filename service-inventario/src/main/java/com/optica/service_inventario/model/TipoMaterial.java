package com.optica.service_inventario.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tipo_material")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "modelo que representa el material de un lente")
public class TipoMaterial {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "debe ingresar un material ya existente",example = "metalico plastico",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre; //Meetalico, plastico, etc

}
