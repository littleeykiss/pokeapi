# 🧪 PokeAPI Backend - Spring Boot

¡Bienvenido entrenador Pokémon! Este microservicio está hecho con Java para que puedas consultar información de cualquier Pokémon usando la [PokeAPI](https://pokeapi.co/), con una API REST limpia, estructurada y documentada.

---

## 🚀 Tecnologías utilizadas

| Backend       | Otros        | Testing       |
|---------------|--------------|---------------|
| Java 17       | Swagger UI   | JUnit 5       |
| Spring Boot 3 | Docker       | Mockito       |
| Spring Web    | H2 Database  | MockMvc       |
| Spring JPA    | Lombok       |               |

---

## 🎯 ¿Qué hace este microservicio?

- Consulta la PokeAPI sin necesidad de autenticación
- Devuelve información filtrada y organizada:
  - 🧬 Nombre, especie, tipo(s), habilidades, ataques
  - 📊 Estadísticas
  - 🖼️ Imagen en base64
- Guarda el historial de búsquedas
- Expone una API REST documentada

---

## 🔧 Cómo ejecutar localmente

### 🖥️ Requisitos previos
- Java 17
- Maven 3.8+

### 📦 Paso a paso
```bash
# Clona el repositorio
mvn clean install
mvn spring-boot:run
```

### 🔗 Accede a Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🐳 Docker

```bash
mvn clean package
# Construye la imagen
docker build -t pokeapi .
# Ejecuta el contenedor
docker run -p 8080:8080 pokeapi
```

---

## 📌 Endpoints disponibles

| Método | Endpoint               | Descripción                          |
|--------|------------------------|--------------------------------------|
| GET    | /pokemon/{nombre}     | Consulta un Pokémon por su nombre   |
| GET    | /pokemon/busquedas     | Lista el historial de búsquedas     |

---

## 🧠 Tiempo estimado
⏱️ Aproximadamente **4.5 horas**, incluyendo pruebas, documentación y Docker.

---

## 🧑‍💻 Autor

Erik Sánchez López  
📧 ersalo123@gmail.com  
💼 Java Backend Developer especializado en microservicios bancarios  

---
