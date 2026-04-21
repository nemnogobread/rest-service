ARG BUILD_IMAGE=rest-service-build:latest
FROM ${BUILD_IMAGE} AS artifact

FROM eclipse-temurin:21-jre-alpine

RUN addgroup -g 1000 -S spring \
    && adduser -u 1000 -S spring -G spring \
    && mkdir -p /app/static \
    && chown -R spring:spring /app \
    && rm -rf /var/cache/apk/* /tmp/* \
    && apk cache clean 2>/dev/null || true

WORKDIR /app
COPY --from=artifact --chown=spring:spring /app/target/rest-service-0.0.1-SNAPSHOT.jar app.jar

USER spring:spring
EXPOSE 8090
VOLUME ["/app/static"]
ENTRYPOINT ["java", "-jar", "app.jar"]
