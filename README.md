# springdatareactive

A simple activity tracking application using Spring data couchbase and Spring boot

## Setup

Create a bucket called `activitytracker` and add a primary N1QL index.

Create a User with username `activitytracker` and password `password` 

Change in file `application.properties` the value for `spring.couchbase.bootstrap-hosts` properties with ip adresse in a Couchbase Node.

## To Run

`mvn spring-boot:run`