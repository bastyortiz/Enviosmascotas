package com.duoc.enviosmascotas.model;


public class Envio {
    // Atributos privados 
    private Long id;
    private String producto;
    private String cliente;
    private String direccionDestino;
    private String estado;
    private String ubicacionActual;
    private String fechaEstimada;
    private String repartidorAsignado;

    // Constructor vacío 
    public Envio() {}

    // Constructor con todos los parámetros 
    public Envio(Long id, String producto, String cliente, String direccionDestino, 
                 String estado, String ubicacionActual, String fechaEstimada, String repartidorAsignado) {
        this.id = id;
        this.producto = producto;
        this.cliente = cliente;
        this.direccionDestino = direccionDestino;
        this.estado = estado;
        this.ubicacionActual = ubicacionActual;
        this.fechaEstimada = fechaEstimada;
        this.repartidorAsignado = repartidorAsignado;
    }

    // --- GETTERS 
    
    public Long getId() { return id; }
    public String getProducto() { return producto; }
    public String getCliente() { return cliente; }
    public String getDireccionDestino() { return direccionDestino; }
    public String getEstado() { return estado; }
    public String getUbicacionActual() { return ubicacionActual; }
    public String getFechaEstimada() { return fechaEstimada; }
    public String getRepartidorAsignado() { return repartidorAsignado; }

    // --- SETTERS 

    public void setId(Long id) { this.id = id; }
    public void setProducto(String producto) { this.producto = producto; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }
    public void setFechaEstimada(String fechaEstimada) { this.fechaEstimada = fechaEstimada; }
    public void setRepartidorAsignado(String repartidorAsignado) { this.repartidorAsignado = repartidorAsignado; }
}