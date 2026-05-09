# Envios Mascotas

Microservicio Spring Boot para seguimiento de envios de mascotas con CRUD sobre Oracle, respuestas JSON y enlaces HATEOAS en el endpoint base `/envios`.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Spring HATEOAS
- Oracle Database + Wallet
- H2 para pruebas
- Docker
- JUnit 5 y Mockito

## Endpoint base

- `GET /envios`
- `GET /envios/{id}`
- `POST /envios`
- `PUT /envios/{id}`
- `DELETE /envios/{id}`

Puerto usado:

- `8080`

## Oracle Cloud

La aplicacion usa Wallet Oracle mediante `TNS_ADMIN`.

Configuracion esperada en `src/main/resources/application.properties`:

- `spring.datasource.url=jdbc:oracle:thin:@mibd_high`
- `spring.datasource.hikari.data-source-properties.oracle.net.tns_admin=${TNS_ADMIN:C:/Wallet_MIBD}`

Ejemplo en PowerShell:

```powershell
$env:TNS_ADMIN="C:\Wallet_MIBD"
.\mvnw.cmd spring-boot:run
```

## Pruebas JUnit

```powershell
.\mvnw.cmd test
```

## Generar JAR

```powershell
.\mvnw.cmd clean package
```

## Docker

Construccion:

```powershell
docker build -t enviosmascotas .
```

Ejecucion:

```powershell
docker rm -f enviosmascotas-app
docker run -d `
  --name enviosmascotas-app `
  -p 8080:8080 `
  -e TNS_ADMIN=/opt/oracle/wallet `
  -v C:\Wallet_MIBD:/opt/oracle/wallet `
  enviosmascotas
```

Validacion:

```powershell
Invoke-RestMethod http://localhost:8080/envios | ConvertTo-Json -Depth 10
```

## Postman

Ejemplo `POST /envios`:

```json
{
  "numeroSeguimiento": "ENV-001",
  "estado": "EN_PREPARACION",
  "ubicacionActual": "Santiago",
  "destinatario": "Juan Perez",
  "descripcionProducto": "Alimento para perro"
}
```

Ejemplo `PUT /envios/{id}`:

```json
{
  "estado": "En Transito",
  "ubicacionActual": "Ruta a Santiago"
}
```

## HATEOAS

Las respuestas de consulta muestran `_embedded` y `_links`, incluyendo `self` y `collection`.

## Script SQL

Script Oracle principal:

- `database/enviosmascotas_oracle.sql`

Script adicional de entrega:

- `script_db_envios.sql`

## Cloudflare Tunnel

Para exponer temporalmente la app:

```powershell
docker rm -f enviosmascotas-tunnel
docker run -d `
  --name enviosmascotas-tunnel `
  cloudflare/cloudflared:latest tunnel `
  --url http://host.docker.internal:8080
```

Luego revisar la URL con:

```powershell
docker logs enviosmascotas-tunnel
```
