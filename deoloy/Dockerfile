FROM openjdk:11-slim-buster
WORKDIR /hello-world-app
COPY build/libs/Auth-Service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "Auth-Service-0.0.1-SNAPSHOT.jar"]
