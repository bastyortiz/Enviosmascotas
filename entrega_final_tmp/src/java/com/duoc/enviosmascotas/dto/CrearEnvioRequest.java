package com.duoc.enviosmascotas.dto;

import jakarta.validation.constraints.NotBlank;

public class CrearEnvioRequest {

    @NotBlank(message = "El numero de seguimiento es obligatorio")
    private String numeroSeguimiento;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "La ubicacion actual es obligatoria")
    private String ubicacionActual;

    @NotBlank(message = "El destinatario es obligatorio")
    private String destinatario;

    @NotBlank(message = "La descripcion del producto es obligatoria")
    private String descripcionProducto;

    public String getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public void setNumeroSeguimiento(String numeroSeguimiento) {
        this.numeroSeguimiento = numeroSeguimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
}
