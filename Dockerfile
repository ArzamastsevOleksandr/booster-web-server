FROM eclipse-temurin:21 AS builder
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} booster-web-server.jar
RUN java -Djarmode=layertools -jar booster-web-server.jar extract

FROM eclipse-temurin:21
RUN useradd spring
USER spring
WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

#FROM eclipse-temurin:21
#WORKDIR workspace
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} booster-web-server.jar
#ENTRYPOINT ["java", "-jar", "booster-web-server.jar"]