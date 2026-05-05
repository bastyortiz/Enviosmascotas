package com.duoc.enviosmascotas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SEGUIMIENTO_ENVIOS")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El numero de seguimiento es obligatorio")
    @Column(nullable = false, unique = true)
    private String numeroSeguimiento;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "La ubicacion actual es obligatoria")
    @Column(nullable = false)
    private String ubicacionActual;

    @NotBlank(message = "El destinatario es obligatorio")
    @Column(nullable = false)
    private String destinatario;

    @NotBlank(message = "La descripcion del producto es obligatoria")
    @Column(name = "DETALLE_PRODUCTO", nullable = false)
    private String descripcionProducto;

    public Envio() {}

    public Envio(Long id, String numeroSeguimiento, String estado, String ubicacionActual,
                 String destinatario, String descripcionProducto) {
        this.id = id;
        this.numeroSeguimiento = numeroSeguimiento;
        this.estado = estado;
        this.ubicacionActual = ubicacionActual;
        this.destinatario = destinatario;
        this.descripcionProducto = descripcionProducto;
    }

    public Long getId() { return id; }
    public String getNumeroSeguimiento() { return numeroSeguimiento; }
    public String getEstado() { return estado; }
    public String getUbicacionActual() { return ubicacionActual; }
    public String getDestinatario() { return destinatario; }
    public String getDescripcionProducto() { return descripcionProducto; }

    public void setId(Long id) { this.id = id; }
    public void setNumeroSeguimiento(String numeroSeguimiento) { this.numeroSeguimiento = numeroSeguimiento; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }
}
