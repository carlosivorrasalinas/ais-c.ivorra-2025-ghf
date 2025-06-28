# Imagen base ligera con Java 21
FROM eclipse-temurin:21-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiamos el jar ya compilado
COPY target/nitflex-web-bbdd-rest-*.jar app.jar

# Puerto en el que se ejecutará la app
EXPOSE 8080

# Comando que lanza la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
