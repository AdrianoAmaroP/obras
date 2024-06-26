# Build
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Copiar o código fonte e o arquivo pom.xml
COPY src /app/src
COPY pom.xml /app

# Definir diretorio
WORKDIR /app

# Executar a construção do projeto
RUN mvn clean install -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jre-alpine

# copiar jar para imagem
COPY --from=build /app/target/gerenciador-0.0.1-SNAPSHOT.jar /app/app.jar

# Definir diretorio
WORKDIR /app

# Expor a porta
EXPOSE 8080

# Ao iniciar imagem
CMD ["java", "-jar", "app.jar"]

