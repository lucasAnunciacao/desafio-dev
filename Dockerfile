FROM adoptopenjdk/openjdk11-openj9
ADD ./target/. /microservice
COPY ./target/desafio-dev-0.0.1-SNAPSHOT.jar ./microservice
WORKDIR /microservice
CMD ["java","-jar","desafio-dev-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
