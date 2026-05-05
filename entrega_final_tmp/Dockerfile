FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/enviosmascotas-0.0.1-SNAPSHOT.jar app.jar

ENV SERVER_PORT=8080
ENV TNS_ADMIN=/opt/oracle/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
