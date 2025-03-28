# Imagen base de OpenJDK
FROM openjdk:21-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR de la aplicación
COPY target/ms-auth-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8087

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
