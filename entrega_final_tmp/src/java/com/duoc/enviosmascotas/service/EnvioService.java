package com.duoc.enviosmascotas.service;

import com.duoc.enviosmascotas.dto.ActualizarEnvioRequest;
import com.duoc.enviosmascotas.dto.CrearEnvioRequest;
import com.duoc.enviosmascotas.model.Envio;
import com.duoc.enviosmascotas.repository.EnvioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Transactional(readOnly = true)
    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Envio buscarPorId(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Envio no encontrado"));
    }

    @Transactional
    public Envio guardar(CrearEnvioRequest nuevoEnvio) {
        if (envioRepository.existsByNumeroSeguimiento(nuevoEnvio.getNumeroSeguimiento())) {
            throw new ResponseStatusException(BAD_REQUEST, "El numero de seguimiento ya existe");
        }

        Envio envio = new Envio();
        envio.setNumeroSeguimiento(nuevoEnvio.getNumeroSeguimiento());
        envio.setEstado(nuevoEnvio.getEstado());
        envio.setUbicacionActual(nuevoEnvio.getUbicacionActual());
        envio.setDestinatario(nuevoEnvio.getDestinatario());
        envio.setDescripcionProducto(nuevoEnvio.getDescripcionProducto());
        return envioRepository.save(envio);
    }

    @Transactional
    public Envio actualizar(Long id, ActualizarEnvioRequest request) {
        Envio envio = buscarPorId(id);
        boolean actualizado = false;

        if (request.getEstado() != null && !request.getEstado().isBlank()) {
            envio.setEstado(request.getEstado());
            actualizado = true;
        }

        if (request.getUbicacionActual() != null && !request.getUbicacionActual().isBlank()) {
            envio.setUbicacionActual(request.getUbicacionActual());
            actualizado = true;
        }

        if (!actualizado) {
            throw new ResponseStatusException(BAD_REQUEST, "Debe informar estado, ubicacionActual o ambos");
        }

        return envioRepository.save(envio);
    }

    @Transactional
    public void eliminar(Long id) {
        Envio envio = buscarPorId(id);
        envioRepository.delete(envio);
    }
}
