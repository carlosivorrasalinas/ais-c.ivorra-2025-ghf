# Usa JDK 21 como base
FROM eclipse-temurin:21-jdk

# Crea directorio de trabajo
WORKDIR /app

# Copia el pom y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia todo el código fuente
COPY src ./src

# Compila el proyecto
RUN mvn package -DskipTests

# Expone el puerto
EXPOSE 8080

# Ejecuta la app
CMD ["java", "-jar", "target/nitflex-web-bbdd-rest-1.0.0.jar"]
