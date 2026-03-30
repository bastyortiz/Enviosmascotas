package com.duoc.enviosmascotas.controller;

import com.duoc.enviosmascotas.model.Envio;
import com.duoc.enviosmascotas.service.EnvioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/envios")
public class Enviocontroller {

    private final EnvioService envioService;

    public Enviocontroller(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> listarTodos() {
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Envio verDetalle(@PathVariable Long id) {
        return envioService.buscarPorId(id);
    }

    @PostMapping
    public Envio registrar(@RequestBody Envio nuevoEnvio) {
        return envioService.guardar(nuevoEnvio);
    }

    @PutMapping("/{id}/estado")
    public Envio actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        return envioService.actualizarEstado(id, nuevoEstado);
    }
} 