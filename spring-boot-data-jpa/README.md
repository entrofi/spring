# Spring Boot, JPA with Docker Backed Database
This example demostrates a Spring Boot setup with spring-data-jpa which is backed by Docker Database images which contain initial data to ease-up local development. 

The core dependencies of the example are as follows: 
* Spring Boot 2.5.0
* Spring 5.3.7
* Hibernate 5.4.31.Final
* PostgreSQL driver 42.2.20
* MySQL connector 8.0.25 (Alternative  Database Option)

We are going to follow the listed steps throughout this example
1. Introduce PostgreSQL database as the default database to the application
2. Create and run a PostgreSQL docker image backed by initial data
1. Add entities and repositories to the application
1. Test the initial setup
1. Introduce MySQL database as the secondary database option to the application
1. Create and run a MySQL docker image backed by initial data
1. Compose a runtime environment for the application using Docker compose

Let's start with the initial maven setup for a spring-data-jpa backed Spring boot application: 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>net.entrofi.spring</groupId>
	<artifactId>spring-boot-data-jpa</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-data-jpa</name>
	<description>Spring Boot with Spring Data JPA example</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
		<!-- Database and JPA Dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
   ```

## Introduce PostgreSQL database to the Spring boot application
In order for our application to be run against a postgresql database, we need to introduce postgresql driver dependency to our pom.xml Add the following snippet to the `dependencies` section of your pom file. 
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```
After adding PostgreSQL driver, we need to configure spring datasource: 
```properties
## Connection pool
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5

## PostgreSQL Connection Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_data_jpa_postgres_db
spring.datasource.username=postgres
spring.datasource.password=password

#Let hibernate create the database tables using entity declarations. Of course one should disable this in production environments
#spring.jpa.hibernate.ddl-auto=create
```
## Create and run a PostgreSQL docker image backed by initial data
Creating a PostgreSQL docker image with initial data in it is pretty easy. Add the following `Dockerfile` under the folder `src/docker/databases/postgresql`: 
```
# Dockerfile for PostgreSQL
FROM postgres

ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB spring_data_jpa_postgres_db

COPY scripts/* /docker-entrypoint-initdb.d/
```
The last line in this `Dockerfile` is required to create our initial data. PostgreSQL docker image executes all of the files located under `docker-entrypoint-initdb.d/` alphabetically when the container starts up. Therefore, we are going place all of our initialization scripts under the folder `src/docker/databases/postgresql/scripts`. 

Next step is to create schema initialization and data initialization scripts. First, create a file named `0_create_tables.sql` under `/src/docker/databases/postgresql/scripts/`:
```sql
create sequence hibernate_sequence start 1 increment 1;
create table product
(
    id          int8 not null,
    description text,
    ean         varchar(255),
    name        varchar(255),
    primary key (id)
);
```

and then add the following data initialization script `src/docker/databases/postgresql/scripts/1_initial_data.sql`:
```sql
INSERT INTO public.product (id, description, ean, name) VALUES (1, '<div id="feature-bullets" class="a-section a-spacing-medium a-spacing-top-small">
       <hr>
       <h1 class="a-size-base-plus a-text-bold">
       About this item
       </h1>
       <ul class="a-unordered-list a-vertical a-spacing-mini">
       <li><span class="a-list-item">
       A brushless motor for more power and a longer term than with a conventional carbon brush engine
       </span></li>
       <li><span class="a-list-item">
       Small and easy for convenient handling and at the same time powerful with a high torque for fast screwing. With Robust 1/2 "Outside Fiercant Recording
       </span></li>                                   
       </ul>
       </div>', 'B01M1RJU2O', 'Einhell cordless impact wrench TE-CW 18 Li BL Solo Power X-Change (lithium ion, 18 V, 215 Nm, LED light, bit adapter for screwing, without battery and charger)');
```
Now we can build and run the PostgreSQL docker image: 
```shell
$ docker build -t spring-boot-postgresql-db .
$ docker run --name spring-boot-postgresql-db-ins  -d -p 5432:5432 spring-boot-postgresql-db
```

## Add entities and repositories to the application
We declared a table with name `PRODUCT` in the previous section. Let's create the entity to represent this table in JPA context: 
```java
package net.entrofi.spring.springbootdatajpa.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String eAN;

    private String description;
}
```
Next step is to create the spring data repository to provide basic CRUD functionality for this entity: 
```java
package net.entrofi.spring.springbootdatajpa.data.repository;

import net.entrofi.spring.springbootdatajpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```
As the last step, we can add a controller for testing purposes:
```java
@RestController
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> getProducts() {
        return productRepository.findAll();
    }
}
```
## Test the initial setup
We are now ready to test the application. Run your application `$mvn spring-boot:run` and send the following request to check whether your databases started as expected: 
```shell
$  curl http://localhost:8080/products
[
    {
        "id": 1,
        "name": "Einhell cordless impact wrench TE-CW 18 Li BL Solo Power X-Change (lithium ion, 18 V, 215 Nm, LED light, bit adapter for screwing, without battery and charger)",
        "description": "<div id=\"feature-bullets\" class=\"a-section a-spacing-medium a-spacing-top-small\">.....                                   </div>",
        "ean": "B01M1RJU2O"
    }
]
```

## Introduce MySQL database as the secondary database option to the application
Similar to PostgreSQL step above, we are going to introduce MySQL as the secondary RDBMS option to our application. Create the folder `src/docker/databases/mysql/` to collect your MySQL related configurations and add the following files accordingly, as we did in the PostgreSQL docker image section:

### Dockerfile
```shell
#src/docker/databases/mysql/Dockerfile
FROM mysql:5.7
LABEL description="My Custom Mysql Docker Image"

# Add a database
ENV MYSQL_DATABASE spring_data_jpa_mysql_db

#Check out docker entry point for further configuration :
# https://github.com/docker-library/mysql
COPY ./scripts/ /docker-entrypoint-initdb.d/
```
### Schema creation: 
```sql
--0_create_tables.sql
create table product
(
    id          bigint not null,
    description text,
    ean         varchar(255),
    name        varchar(255),
    primary key (id)
);
```
### Initial data: 
```sql
INSERT INTO product (id, description, ean, name) VALUES (1, '<div id="feature-bullets" class="a-section a-spacing-medium a-spacing-top-small">
       <hr>
       <h1 class="a-size-base-plus a-text-bold">
       About this item
       </h1>
       <ul class="a-unordered-list a-vertical a-spacing-mini">
       <li><span class="a-list-item">
       A brushless motor for more power and a longer term than with a conventional carbon brush engine
       </span></li>
       </ul>
       </div>', 'B01M1RJU2O', 'MYSQL impact wrench');
```
### Run MySQL docker container  
We can now build the customized MySQL docker image and run it: 

```shell
$ docker build -t spring-boot-mysql-db .
$ docker run -d -p 63306:3306 --name spring-boot-mysql-db-ins \
-e MYSQL_ROOT_PASSWORD=password spring-boot-mysql-db
```

### Introduce a new environment specific properties file
As you might already know, spring has a built-in support for targetting different environments. One can simply define `application-[ENVIRONMENT].properties` file and use this  file by defining the a spring profile with same environment name. This is what we are going to do here.
Add  a file named `src/main/resources/application-mysql.properties`: 
```properties
## MySQL
spring.datasource.url=jdbc:mysql://localhost:63306/spring_data_jpa_mysql_db
spring.datasource.username=root
spring.datasource.password=password

#`hibernate_sequence' doesn't exist
spring.jpa.hibernate.use-new-id-generator-mappings=false
```

You can now run your application by activating the `mysql` profile:
```shell
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```





