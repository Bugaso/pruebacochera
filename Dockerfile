# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado en la imagen
COPY target/cocheras-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto 8080 (por defecto en Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
