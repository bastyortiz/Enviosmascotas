# PROMPT CODEX - EFT SEMANA 9 - MICROSERVICIO ENVIOS MASCOTAS

## Objetivo

Aplicar los requisitos finales de la Semana 9 al microservicio `enviosmascotas`.

Este proyecto ya funciona con Spring Boot, Oracle Cloud, Docker, HATEOAS y endpoint `/envios`. No lo rehagas desde cero.

La tarea es revisar, corregir y completar lo necesario para que cumpla la Evaluación Final Transversal de Semana 9.

---

## Requisitos Semana 9 que debe cumplir este microservicio

El microservicio debe:

- Estar desarrollado con Spring Boot.
- Comunicarse mediante CRUD con una base de datos Oracle.
- Devolver JSON en sus endpoints.
- Ser probado mediante Postman.
- Tener documentación HATEOAS visible en las respuestas.
- Tener al menos 4 pruebas unitarias con JUnit.
- Incluir script SQL Oracle de su base de datos.
- Ejecutarse correctamente en Docker.
- Quedar listo para ser expuesto con Cloudflare Tunnel o Cloud.
- Mantener el endpoint funcional `/envios`.

---

## Estado actual conocido

Ya se logró:

- Spring Boot funcionando.
- Oracle Cloud conectado.
- Docker funcionando.
- Endpoint base: `/envios`.
- URL local Docker: `http://localhost:8080/envios`.
- HATEOAS visible con `_embedded`, `_links`, `self`, `collection`.
- JAR generado correctamente.
- Tests ejecutando.
- Actualmente existen pruebas, pero Semana 9 exige mínimo 4 pruebas unitarias.

---

## Diagnóstico inicial obligatorio

Antes de modificar, revisa y responde:

1. Si el proyecto compila.
2. Si `./mvnw.cmd test` funciona.
3. Cuántas pruebas JUnit existen actualmente.
4. Si hay mínimo 4 pruebas unitarias.
5. Si el CRUD está completo.
6. Si existe conexión Oracle Cloud.
7. Si existe script SQL Oracle.
8. Si existe Dockerfile.
9. Si existe `.dockerignore`.
10. Si HATEOAS está funcionando.
11. Si devuelve JSON correctamente.
12. Si tiene validaciones.
13. Si el README está actualizado.
14. Qué falta para cumplir Semana 9.

Después del diagnóstico, aplica los cambios necesarios.

---

## Requisito crítico: mínimo 4 pruebas unitarias

Este microservicio debe tener al menos 4 pruebas unitarias reales con JUnit.

No basta con 2 ni con 3.

Las pruebas deben ejecutarse con:

```powershell
.\mvnw.cmd test
```

Y deben terminar con:

```txt
BUILD SUCCESS
Failures: 0
Errors: 0
```

---

## Pruebas recomendadas

Crear o completar pruebas para cubrir como mínimo 4 de estos casos:

1. Listar envíos.
2. Buscar envío por ID.
3. Crear envío.
4. Actualizar estado o ubicación del envío.
5. Eliminar envío.
6. Validar error cuando el envío no existe.
7. Validar datos obligatorios.

Nombres sugeridos:

```java
deberiaListarEnvios()
deberiaBuscarEnvioPorId()
deberiaCrearEnvio()
deberiaActualizarEnvio()
deberiaEliminarEnvio()
deberiaRetornarErrorSiEnvioNoExiste()
```

Preferencia:

- Usar mocks o perfil `test`.
- No depender de Oracle Cloud real para las pruebas.
- Usar H2 si ya existe perfil de pruebas.
- No romper pruebas existentes.

---

## CRUD obligatorio

Mantener endpoint base:

```txt
/envios
```

Debe funcionar:

```txt
GET    /envios
GET    /envios/{id}
POST   /envios
PUT    /envios/{id}
DELETE /envios/{id}
```

Si ya existe un endpoint específico para actualizar estado o ubicación, mantenerlo solo si no rompe el CRUD principal.

---

## JSON esperado

El endpoint debe devolver JSON.

Ejemplo esperado:

```json
{
  "id": 1,
  "numeroSeguimiento": "SEG-001",
  "estado": "En Transito",
  "ubicacionActual": "Santiago",
  "destinatario": "Bastian Ortiz",
  "descripcionProducto": "Comida Mascotas",
  "_links": {
    "self": {
      "href": "http://localhost:8080/envios/1"
    },
    "collection": {
      "href": "http://localhost:8080/envios"
    }
  }
}
```

---

## HATEOAS

No quitar HATEOAS.

Debe seguir apareciendo:

```txt
_embedded
_links
self
collection
```

En listado debe verse algo como:

```json
{
  "_embedded": {
    "envioList": []
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/envios"
    }
  }
}
```

---

## Validaciones

Revisar que existan validaciones básicas.

Campos sugeridos:

```java
@NotBlank
private String numeroSeguimiento;

@NotBlank
private String estado;

@NotBlank
private String ubicacionActual;

@NotBlank
private String destinatario;

@NotBlank
private String descripcionProducto;
```

Los métodos `POST` y `PUT` deben usar:

```java
@Valid
```

---

## Manejo de errores

Agregar o revisar manejo básico para:

- ID inexistente.
- Datos inválidos.
- Recurso no encontrado.
- Errores de validación.

Opcional recomendado:

```java
@RestControllerAdvice
```

No sobrecomplicar.

---

## Oracle Cloud

No romper la conexión Oracle que ya funciona.

El microservicio debe seguir funcionando con Wallet Oracle.

Docker esperado:

```powershell
docker run -d `
  --name enviosmascotas-app `
  -p 8080:8080 `
  -e TNS_ADMIN=/opt/oracle/wallet `
  -v C:\Wallet_MIBD:/opt/oracle/wallet `
  enviosmascotas
```

Endpoint local Docker:

```txt
http://localhost:8080/envios
```

---

## Script SQL Oracle

Debe existir un script SQL para este microservicio, por ejemplo:

```txt
database/enviosmascotas_oracle.sql
```

Debe incluir:

- Creación de tabla.
- Llave primaria.
- Campos obligatorios.
- Inserts con mínimo 3 registros.
- `COMMIT;`

No mezclarlo con el script de citas médicas.

---

## Docker

Mantener o corregir Dockerfile.

Debe permitir:

```powershell
.\mvnw.cmd clean package
docker build -t enviosmascotas .
docker rm -f enviosmascotas-app
docker run -d `
  --name enviosmascotas-app `
  -p 8080:8080 `
  -e TNS_ADMIN=/opt/oracle/wallet `
  -v C:\Wallet_MIBD:/opt/oracle/wallet `
  enviosmascotas
```

Validación:

```powershell
Invoke-RestMethod http://localhost:8080/envios | ConvertTo-Json -Depth 10
```

---

## Cloudflare Tunnel

No inventes URL pública.

Solo documenta que se puede levantar así:

```powershell
docker rm -f enviosmascotas-tunnel
docker run -d `
  --name enviosmascotas-tunnel `
  cloudflare/cloudflared:latest tunnel `
  --url http://host.docker.internal:8080
```

Luego se obtiene la URL con:

```powershell
docker logs enviosmascotas-tunnel
```

---

## README

Actualizar README con:

- Descripción del microservicio.
- Tecnologías usadas.
- Configuración Oracle Cloud.
- Comandos para pruebas JUnit.
- Comandos para generar JAR.
- Comandos Docker.
- Endpoint base `/envios`.
- Ejemplos Postman.
- Explicación de HATEOAS.
- Ubicación del script SQL.
- Puerto usado: `8080`.

No agregar URL pública fija, porque Cloudflare genera URL temporal.

---

## Checklist final

Confirma al terminar:

- [ ] Compila.
- [ ] `mvnw.cmd test` funciona.
- [ ] Tiene mínimo 4 pruebas unitarias.
- [ ] CRUD completo.
- [ ] Oracle Cloud funcionando.
- [ ] Docker funcionando.
- [ ] Endpoint `/envios` responde JSON.
- [ ] HATEOAS visible.
- [ ] Script SQL incluido.
- [ ] README actualizado.
- [ ] Proyecto listo para Postman y video.

---

## Resultado que debes devolver

Al finalizar, responde:

1. Cambios aplicados.
2. Archivos modificados.
3. Archivos nuevos creados.
4. Cantidad final de pruebas JUnit.
5. Resultado esperado de `mvnw.cmd test`.
6. Endpoint base final.
7. Comando Docker final.
8. Script SQL incluido.
9. Puntos que debo revisar manualmente.

---

## Restricciones

- No rehacer desde cero.
- No cambiar `/envios` si ya funciona.
- No cambiar puerto 8080 si no es necesario.
- No romper Oracle Cloud.
- No romper Docker.
- No romper HATEOAS.
- No dejar menos de 4 pruebas unitarias.
- No subir credenciales.
- No incluir Wallet Oracle dentro del repositorio.
