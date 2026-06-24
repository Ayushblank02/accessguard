FROM eclipse-temurin:23-jre

WORKDIR /app

COPY target/accessguard.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]