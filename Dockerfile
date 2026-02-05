FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# ✅ Скачиваем Maven
RUN apk add --no-cache maven

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# ✅ Maven сборка
RUN mvn clean package -DskipTests

# Production
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/actuator/health || exit 1
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
