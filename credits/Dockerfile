# creditos-api/Dockerfile

# 1) Build stage
FROM gradle:7.5-jdk17 AS builder
WORKDIR /app

# só copia o que existe para acelerar cache de dependências
COPY build.gradle settings.gradle ./
RUN gradle --no-daemon dependencies

# copia o código-fonte e gera o jar
COPY src ./src
RUN gradle --no-daemon clean build -x test

# 2) Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
