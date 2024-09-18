FROM amazoncorretto:21
LABEL author="vflopes"
WORKDIR /app
COPY javamail-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]