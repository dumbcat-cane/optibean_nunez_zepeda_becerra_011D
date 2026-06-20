package com.optica.service_envio.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long idEnvio;

    @Column(name = "fecha_despacho")
    private String fechaDespacho;

    @Column(name = "fecha_entrega")
    private String fechaEntrega;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_notificacion")
    private Notificacion notificacion;

    private Long idVenta;

    @Transient
    private Object datosVenta;

}
