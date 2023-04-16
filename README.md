# Ecommerce App Documentation

## Authenticate User
This endpoint authenticates a user with their credentials.
> POST /api/auth/signin
### Request Body
The request body must be a JSON object containing the following fields:
- username (required): The user's username.
- password (required): The user's password.
Example request body:
<pre>
{
    "username": "johndoe",
    "password": "password123"
}
</pre>
### Response
If the user is successfully authenticated, the server will respond with a JWT token in the response body.
### Response Body
The response body will contain a JSON object with the following fields:
- token (required): The JWT token.
Example response body:
<pre>
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
</pre>


## Register User
This endpoint registers a new user.
> POST /api/auth/signup
### Request Body
The request body must be a JSON object containing the following fields:
- username (required): The user's username.
- email (required): The user's email address.
- password (required): The user's password.
<pre>
{
    "username": "johndoe",
    "email": "johndoe@example.com",
    "password": "password123"
}
</pre>
### Response
If the user is successfully registered, the server will respond with a success message.
### Response Body
The response body will contain a JSON object with the following fields:
- token (required): The JWT token.
  Example response body:
<pre>
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
</pre>

## Process Forgot Password
This endpoint authenticates a user with their credentials.
> POST /api/auth/reset-password
### Request Parameters
- email (required): The email address of the user.
### Response Body
The response body will contain a JSON object with the following fields:
- message: Token successfully sent to email!
Example response body:
<pre>
{
    "message": "Token successfully sent to email!"
}
</pre>

##  Submit Forgot Password
This endpoint authenticates a user with their credentials.
> POST /api/auth/reset-password/submit
### Request Parameters
- newPassword (required): The new password that the user wants to set.
- confirmNewPassword (required): The confirmation of the new password.
- token (required): The password reset token sent to the user's email address.
### Response Body
The response body will contain a JSON object with the following fields:
- message: Token successfully sent to email!
  Example response body:
<pre>
{
    "message": "You have successfully changed your password."
}
</pre>

## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= postgres

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

# App Properties
app.jwtSecret= Abc1234!
app.jwtExpirationMs= 86400000
```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
app.jwtSecret= Abc1234!
app.jwtExpirationMs= 86400000
```
## Run Spring Boot application
```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_SELLER');
```
