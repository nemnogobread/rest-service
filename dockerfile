FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -e -B dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -e -B clean package -DskipTests \
    && rm -rf /var/cache/apk/* /tmp/*

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -g 1000 -S spring \
    && adduser -u 1000 -S spring -G spring \
    && mkdir -p /app/static \
    && chown -R spring:spring /app \
    && rm -rf /var/cache/apk/*

WORKDIR /app
COPY --from=build --chown=spring:spring /app/target/rest-service-0.0.1-SNAPSHOT.jar app.jar

USER spring:spring
EXPOSE 8090
VOLUME ["/app/static"]
ENTRYPOINT ["java", "-jar", "app.jar"]
