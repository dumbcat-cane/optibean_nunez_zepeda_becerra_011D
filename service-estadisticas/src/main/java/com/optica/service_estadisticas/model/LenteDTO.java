package com.optica.service_estadisticas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LenteDTO {
    private Long id;
    private String codigo;
    private int cantidad;

}
