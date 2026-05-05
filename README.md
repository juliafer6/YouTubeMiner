# YouTubeMiner

## Descripción

YouTubeMiner es un microservicio adaptador desarrollado con **Spring Boot** que forma parte de una arquitectura de integración de tres microservicios. Su objetivo es extraer datos de canales de YouTube a través de la **YouTube Data API v3** y enviarlos a **VideoMiner** para su almacenamiento, usando un modelo de datos común.

## Arquitectura

```
YouTube Data API v3 ←→ YouTubeMiner ←→ VideoMiner
```

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.6
- Maven
- YouTube Data API v3
- RestTemplate

## Estructura del proyecto

```
src/main/java/aiss/YouTubeMiner/
├── controller/
│   └── YouTubeMinerController.java
├── etl/
│   └── Transformer.java
├── model/
│   ├── youtube/              ← DTOs de la API de YouTube
│   │   ├── YouTubeChannel.java
│   │   ├── YouTubeVideo.java
│   │   ├── YouTubeComment.java
│   │   └── YouTubeCaption.java
│   └── videominer/           ← Modelo común de VideoMiner
│       ├── Channel.java
│       ├── Video.java
│       ├── Caption.java
│       ├── Comment.java
│       └── User.java
├── service/
│   └── YouTubeMinerService.java
├── exception/
│   └── ChannelNotFoundException.java
└── YouTubeMinerApplication.java
```

## Configuración

Crea el archivo `src/main/resources/application.properties` con el siguiente contenido:

```properties
spring.application.name=YouTubeMiner
server.port=8083
youtube.api.key=TU_API_KEY_AQUI
videominer.base.url=http://localhost:8080
```

> ⚠️ Nunca subas tu API key a GitHub. El archivo `application.properties` debe estar en el `.gitignore`.

Para obtener una API key de YouTube Data API v3:
1. Ve a [Google Cloud Console](https://console.cloud.google.com)
2. Crea un proyecto y activa la **YouTube Data API v3**
3. Crea una credencial de tipo **API Key**

## Endpoints

### GET `/{channelId}`
Obtiene los datos de un canal de YouTube sin enviarlos a VideoMiner. Útil para pruebas.

```
GET http://localhost:8083/youtubemine/{channelId}?maxVideos=10&maxComments=2
```

### POST `/{channelId}`
Obtiene los datos de un canal de YouTube y los envía a VideoMiner.

```
POST http://localhost:8083/youtubemine/{channelId}?maxVideos=10&maxComments=2
```

#### Parámetros opcionales

| Parámetro | Descripción | Valor por defecto |
|---|---|---|
| `maxVideos` | Número máximo de vídeos a obtener | 10 |
| `maxComments` | Número máximo de comentarios por vídeo | 2 |

#### Respuestas

| Código | Descripción |
|---|---|
| 200 | Canal obtenido correctamente |
| 404 | Canal no encontrado |
| 500 | Error interno del servidor |

## Ejemplo de uso

```
GET http://localhost:8083/youtubemine/UC_x5XG1OV2P6uZZ5FSM9Ttw?maxVideos=5&maxComments=2
```

Respuesta:
```json
{
  "id": "UC_x5XG1OV2P6uZZ5FSM9Ttw",
  "name": "Google for Developers",
  "description": "Subscribe to join a community of creative developers...",
  "createdTime": "2007-08-23T00:34:43Z",
  "videos": [
    {
      "id": "bKRe5wu4Fcw",
      "name": "Announcing Gemma 4...",
      "description": "...",
      "releaseTime": "2026-04-30T16:01:06Z",
      "user": { ... },
      "comments": [ ... ],
      "captions": [ ... ]
    }
  ]
}
```

## Modelo de datos

El modelo de datos es compartido con VideoMiner:

- **Channel**: `id`, `name`, `description`, `createdTime`, `videos`
- **Video**: `id`, `name`, `description`, `releaseTime`, `user`, `comments`, `captions`
- **User**: `id`, `name`, `user_link`, `picture_link`
- **Comment**: `id`, `text`, `createdOn`
- **Caption**: `id`, `name`, `language`

## Ejecución

1. Clona el repositorio:
```bash
git clone https://github.com/adrlopech/YouTubeMiner.git
```

2. Configura el `application.properties` con tu API key.

3. Arranca VideoMiner en el puerto 8080.

4. Arranca YouTubeMiner:
```bash
./mvnw spring-boot:run
```

5. Accede a `http://localhost:8083/youtubemine/{channelId}`
