### Preparação Ambiente:
- JDK 17
- Maven
- Postman
- PgAdmin (Postgres)
- IDE (IntelliJ, STS, Eclipse, etc)


- Spring boot 3 e java 17
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Hateoas

### Subindo banco local com docker
 docker run --name products-api -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -p 5432:5432 -d bitnami/postgresql
