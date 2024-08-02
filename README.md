# BlogWeb Spring Boot Application

## Description

BlogWeb is a blog application developed with Spring Boot. It allows users to create, view and search blog posts, as well as like posts. The backend uses a MySQL database to store article data.

## Configuration

### Application properties
The `application.properties` file contains the following configurations:

``properties``
spring.application.name=blogServer
spring.datasource.url=jdbc:mysql://localhost:3306/blog_server
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show.sql=true

server.port=9292
server.servlet.context-path=/api
