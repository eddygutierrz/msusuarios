# Usa la imagen oficial de Java con JDK (la versión que uses)
FROM eclipse-temurin:21-jdk-alpine

# Crea un directorio en la imagen
WORKDIR /app

# Copia el archivo JAR generado por Maven
COPY target/msusuarios-*.jar app.jar

# Expone el puerto que usas (por defecto, 8080)
EXPOSE 8081

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]