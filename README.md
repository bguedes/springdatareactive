# springdatareactive

A simple activity tracking application using Spring data couchbase and Spring boot

## Setup

Create a bucket called `activitytracker`.

Create a User with username `activitytracker` and password `password` 

Change in file `application.properties` the value for `spring.couchbase.bootstrap-hosts` properties with Couchbase Node ip adresse of your cluster.

## To Run

`mvn spring-boot:run`
