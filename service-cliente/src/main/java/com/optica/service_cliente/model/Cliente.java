package com.optica.service_cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String run;
    private String nombres;
    private String apellidos;
}