FROM openjdk:8-alpine

WORKDIR /opt/app/
EXPOSE 8080
COPY ./build/libs/pack-0.0.1-SNAPSHOT.jar /opt/app/

CMD ["java", "-jar", "pack-0.0.1-SNAPSHOT.jar"]
