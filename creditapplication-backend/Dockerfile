FROM maven:3.6.1-jdk-11-slim AS build
RUN mkdir -p workspace
WORKDIR workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean install -DskipTests=true

FROM azul/zulu-openjdk-alpine:11.0.13-jre
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]