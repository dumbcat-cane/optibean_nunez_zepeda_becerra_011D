package com.optica.service_receta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaDTO {
    private Long id;
    private String fecha;
    private String diagnostico;

}
