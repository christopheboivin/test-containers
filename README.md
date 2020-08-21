# test-containers

Testing the [testcontainers](https://www.testcontainers.org) framework with some postgresql database

## Prerequisite
- For building: having docker installed and running 
- For running "in real life":
  - Having a postgresql 12 server running with a user "user-test" matching the application.properties.
  - This user must have the right to create tables.

## Building
`./mvnw package`

## Running
`./mvnw spring-boot:run`  
Go to [http://localhost:8080]()
Create a user :
```shell script
curl -i -H "Content-Type:application/json" -d '{"firstName": "Thom", "lastName": "Yorke"}' http://localhost:8080/users
```
## TODO
