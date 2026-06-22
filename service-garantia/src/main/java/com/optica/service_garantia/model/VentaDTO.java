package com.optica.service_garantia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Long id;
    private String fechaVenta;
    private int Montofecha;

}
