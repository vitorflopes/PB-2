FROM amazoncorretto:21
LABEL author="vflopes"
WORKDIR /app
COPY prescricao-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]