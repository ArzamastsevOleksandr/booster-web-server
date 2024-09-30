# booster-web-server

## Useful commands

| Gradle command                                           | Description                                         |
|----------------------------------------------------------|-----------------------------------------------------|
| `./gradlew bootRun`                                      | Start the app                                       |
| `./gradlew build`                                        | Build the app                                       |
| `./gradlew test`                                         | Run all tests                                       |
| `./gradlew test --test BoosterWebServerApplicationTests` | Run only specified tests                            |
| `./gradlew bootJar`                                      | Package the application as a JAR file               |
| `./gradlew bootBuildImage`                               | Package the application as a Docker container image |

Run the app from the Java CLI:

```bash
java -jar build/libs/booster-web-server-0.0.1-SNAPSHOT.jar
```

Run the app as a Docker container:

```bash
docker network create booster-web-server-network
```

```bash
docker network ls
```

```bash
docker run -d \
    --name booster-web-server-db \
--net booster-web-server-network \
-e POSTGRES_USER=root \
-e POSTGRES_PASSWORD=pass \
-e POSTGRES_DB=booster_web_server \
-p 5455:5432 \
postgres:latest
```

```bash
docker run --rm --name booster-web-server --net booster-network -p 8090:8090 -e SPRING_DATASOURCE_URL=jdbc:postgresql://booster-web-server-db:5432/booster_web_server booster-web-server:0.0.1-SNAPSHOT  
```

```bash
minikube start --driver=docker
```

```bash
minikube stop
```

### Create Deployment for application container

```bash
kubectl create deployment booster-web-server --image=booster-web-server:0.0.1-SNAPSHOT
```

### Create Service for application Deployment

```bash
kubectl expose deployment booster-web-server --name=booster-web-server --port=8090
```

### Port forwarding from localhost to Kubernetes cluster

```bash
kubectl port-forward service/booster-web-server 8090:8090
```


todo:
stop all containers: docker stop $(docker ps -a -q)
remove all containers: docker rm $(docker ps -a -q)

Steps to run the app as a docker container:
1. Start the postgre container
2. Build the jar: `./gradlew clean bootJar`
3. Build the docker image of the app: `docker build -t booster-web-server .`
4. `docker run --rm --name booster-web-server --net booster-network -p 8090:8090 -e SPRING_DATASOURCE_URL=jdbc:postgresql://booster-web-server-db:5432/booster_web_server booster-web-server`