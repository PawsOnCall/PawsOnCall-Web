# PawsOnCall-Web

the Web Project of PawsOnCall

# install docker

https://www.docker.com/products/docker-desktop/

# install Java JDK 22

https://www.oracle.com/ca-en/java/technologies/downloads/

# How to run

```bash
# run postgres with docker
docker run --name pawoncall-db -e POSTGRES_DB=pawoncall -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1bKJDiZFEddbPatkiildXQ== -p 5432:5432 -d postgres

# run spring boot
./mvnw spring-boot:run

# open chrome
http://localhost:8080/

# test login and logout
# after login, you can find all rest-apis @ http://localhost:8080/swagger-ui/index.html
```
