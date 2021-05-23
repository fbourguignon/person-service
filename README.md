# person-service

A sample project using SpringBoot, SpringData and Postgres JSONB

- [x] Spring Boot 2.4.4
- [x] Spring Data
- [x] Lombok
- [x] Flyway
- [x] Docker
- [x] PostgresSQL 11
- [x] TestContainers


JSONB stands for “JSON Binary”. 
It is a decomposed binary format to store JSON. 
JSONB supports indexing the JSON data, and is very efficient at parsing and querying the JSON data.
In most cases, when you work with JSON in PostgreSQL, you should be using JSONB.

From project directory, start up the application by running.

```console
docker-compose up -d
```
Compose pulls and build the images from project, and starts the services.

