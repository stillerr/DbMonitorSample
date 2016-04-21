# DbMonitorSample
DbMonitorSample using Spring boot, web, integration, websocket

## Install
* mvn clean install

## Running
* mvn spring-boot:run
* open http://localhost:8080/
* To add a random event quickly: http://localhost:8080/event/random
* To add a custom event make a POST request: http://localhost:8080/event/save/{something}


## REST endpoints:
* [GET]  /info - list application info
* [GET]  /events - list of the saved events
* [GET]  /events/random - generate a random event (GET because it's easier to use from a browser tab)
* [POST] /events/save/{something} - save an event

## Technologies
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Integration (DSL)
* Spring Websocket

* Hibernate
* H2 (in-memory)

* Guava

* Mockito
* Hamcrest

## Parameters (application.properties)

* datasource.* - connection parameters for the H2 db
* database.poller.periodMs - DB polling period in milliseconds
* app.* - application parameters like version and creator

## About
This is a simple database poller application with REST and websocket endpoints.
It uses an in-memory database, so the data will be erased after shutdown.

!!!
In this form the application handles only the inserts of the BusinessEvents. (Time issues)
Watching the update and delete operations should be implemented in the future.
!!!

