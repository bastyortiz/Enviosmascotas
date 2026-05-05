package com.duoc.enviosmascotas.controller;

import com.duoc.enviosmascotas.dto.ActualizarEnvioRequest;
import com.duoc.enviosmascotas.dto.CrearEnvioRequest;
import com.duoc.enviosmascotas.model.Envio;
import com.duoc.enviosmascotas.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Envio registrar(@Valid @RequestBody CrearEnvioRequest nuevoEnvio) {
        return envioService.guardar(nuevoEnvio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody ActualizarEnvioRequest request) {
        return envioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
