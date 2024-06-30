# PawsOnCall-Web

the Web Project of PawsOnCall

# install docker

https://www.docker.com/products/docker-desktop/

# install Java JDK 22

https://www.oracle.com/ca-en/java/technologies/downloads/

# How to run

```bash
# get cred from https://docs.qq.com/sheet/DWnpHc2JVaWxHd1JD?is_no_hook_redirect=1&tab=x88fn0
export OAUTH2_CLIENT_ID=<redacted>
export OAUTH2_CLIENT_SECRET=<redacted>
export DATASOURCE_PASSWORD=<redacted>
export DATASOURCE_URL=<redacted>
export DATASOURCE_USERNAME=<redacted>

# run postgres with docker
mkdir -p $HOME/local/pgdata
docker rm -f pawoncall-db
docker run -d --name pawoncall-db -e POSTGRES_DB=pawoncall -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD="${DATASOURCE_PASSWORD}"-v "${HOME}"/local/pgdata:/var/lib/postgresql/data -p 5432:5432 --restart always postgres

# run spring boot
./mvnw spring-boot:run

# open chrome
http://localhost:8080/

# test login and logout
# after login, you can find all rest-apis @ http://localhost:8080/swagger-ui/index.html
```

# Checking database
```bash
docker exec -it pawoncall-db bash
# switch user
su - postgres
psql -d pawoncall
# checking tables
\dt
# checking some table
\d+ users
```
