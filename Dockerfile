# Usar una imagen base de Java
FROM eclipse-temurin:17-jre-jammy
 
# Establecer el directorio de trabajo
WORKDIR /app
 
# Copiar el archivo JAR generado
COPY target/pipeline.jar pipeline.jar
 
# Exponer el puerto 8080
EXPOSE 8080
 
# Comando para ejecutar la aplicación
CMD ["java", "-jar", "pipeline.jar"]
