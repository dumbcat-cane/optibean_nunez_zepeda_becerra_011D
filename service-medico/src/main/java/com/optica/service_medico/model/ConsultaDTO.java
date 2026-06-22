package com.optica.service_medico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConsultaDTO {
    private Long id;
    private String fecha;
    private String diagnostico;

}
