package com.duoc.enviosmascotas.service;

import com.duoc.enviosmascotas.dto.CrearEnvioRequest;
import com.duoc.enviosmascotas.dto.ActualizarEnvioRequest;
import com.duoc.enviosmascotas.model.Envio;
import com.duoc.enviosmascotas.repository.EnvioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    private CrearEnvioRequest crearEnvioRequest;
    private Envio envioGuardado;

    @BeforeEach
    void setUp() {
        crearEnvioRequest = new CrearEnvioRequest();
        crearEnvioRequest.setNumeroSeguimiento("TRK-9001");
        crearEnvioRequest.setEstado("Pendiente");
        crearEnvioRequest.setUbicacionActual("Centro logistica Santiago");
        crearEnvioRequest.setDestinatario("Ana Torres");
        crearEnvioRequest.setDescripcionProducto("Mochila de transporte para mascota");

        envioGuardado = new Envio(1L, "TRK-9001", "Pendiente", "Centro logistica Santiago",
                "Ana Torres", "Mochila de transporte para mascota");
    }

    @Test
    void testRegistrarEnvio() {
        when(envioRepository.existsByNumeroSeguimiento("TRK-9001")).thenReturn(false);
        when(envioRepository.save(any(Envio.class))).thenReturn(envioGuardado);

        Envio resultado = envioService.guardar(crearEnvioRequest);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("TRK-9001", resultado.getNumeroSeguimiento());
        assertEquals("Ana Torres", resultado.getDestinatario());
        verify(envioRepository).save(any(Envio.class));
    }

    @Test
    void testConsultarUbicacion() {
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envioGuardado));

        Envio resultado = envioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Centro logistica Santiago", resultado.getUbicacionActual());
        verify(envioRepository).findById(1L);
    }

    @Test
    void testActualizarEnvio() {
        ActualizarEnvioRequest request = new ActualizarEnvioRequest();
        request.setEstado("En transito");
        request.setUbicacionActual("Ruta 68");

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envioGuardado));
        when(envioRepository.save(any(Envio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Envio resultado = envioService.actualizar(1L, request);

        assertEquals("En transito", resultado.getEstado());
        assertEquals("Ruta 68", resultado.getUbicacionActual());
        verify(envioRepository).save(envioGuardado);
    }

    @Test
    void testRetornarErrorSiEnvioNoExiste() {
        when(envioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> envioService.buscarPorId(99L));

        assertEquals(NOT_FOUND, exception.getStatusCode());
        assertEquals("Envio no encontrado", exception.getReason());
        verify(envioRepository, never()).save(any(Envio.class));
    }
}
