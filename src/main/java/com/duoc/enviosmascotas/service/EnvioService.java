package com.duoc.enviosmascotas.service;

import com.duoc.enviosmascotas.model.Envio;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnvioService {

    private List<Envio> envios = new ArrayList<>();

    public EnvioService() {
   
        envios.add(new Envio(1L, "Cama Ortopédica", "Bastian", "Av. Central 123", "En Transito", "Centro Distribución", "2026-04-01", "Juan Pérez"));
        envios.add(new Envio(2L, "Saco Comida 15kg", "Ana Ríos", "Calle Falsa 456", "Pendiente", "Bodega Principal", "2026-03-30", "Sin Asignar"));
        envios.add(new Envio(3L, "Rascador Gato", "Carlos M.", "Pasaje 1, N° 40", "Entregado", "Domicilio Cliente", "2026-03-28", "Pedro Soto"));
        envios.add(new Envio(4L, "Juguete Mordedor", "Lucía H.", "Diagonal 88", "En Transito", "Ruta Las Condes", "2026-03-29", "Juan Pérez"));
        envios.add(new Envio(5L, "Arena Sanitaria", "Roberto V.", "Avenida Matta", "Pendiente", "Bodega Principal", "2026-04-02", "Sin Asignar"));
        envios.add(new Envio(6L, "Arnés Seguridad", "Elena P.", "Cerro Colorado", "En Transito", "Ruta Ñuñoa", "2026-03-30", "Diego Arcos"));
        envios.add(new Envio(7L, "Snacks Saludables", "Mario T.", "Departamental", "Entregado", "Domicilio Cliente", "2026-03-25", "Pedro Soto"));
        envios.add(new Envio(8L, "Champú Neutro", "Sofía L.", "Providencia 10", "Pendiente", "Bodega Principal", "2026-04-05", "Sin Asignar"));
    }

    public List<Envio> obtenerTodos() {
        return envios;
    }

    public Envio buscarPorId(Long id) {
        return envios.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Lógica para registrar un nuevo envío 
    public Envio guardar(Envio nuevoEnvio) {
        envios.add(nuevoEnvio); 
        return nuevoEnvio;
    }

    // Lógica para actualizar el estado 
    public Envio actualizarEstado(Long id, String nuevoEstado) {
        Envio envio = buscarPorId(id);
        if (envio != null) {
            envio.setEstado(nuevoEstado);
        }
        return envio;
    }
} 