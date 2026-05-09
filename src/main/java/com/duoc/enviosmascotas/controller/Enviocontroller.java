package com.duoc.enviosmascotas.controller;

import com.duoc.enviosmascotas.assembler.EnvioModelAssembler;
import com.duoc.enviosmascotas.dto.ActualizarEnvioRequest;
import com.duoc.enviosmascotas.dto.CrearEnvioRequest;
import com.duoc.enviosmascotas.model.Envio;
import com.duoc.enviosmascotas.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    public EnvioController(EnvioService envioService, EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Envio>> listarTodos() {
        List<EntityModel<Envio>> envios = envioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioController.class).listarTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Envio> verDetalle(@PathVariable Long id) {
        return assembler.toModel(envioService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Envio registrar(@Valid @RequestBody CrearEnvioRequest nuevoEnvio) {
        return envioService.guardar(nuevoEnvio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @Valid @RequestBody ActualizarEnvioRequest request) {
        return envioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
