# REST API Simple Movie Search Engine

This is a Simple Movie search engine REST API used for retrieving movie information from MongoDB database.

The Application uses content from `movies.csv` file to populate Database with Movie objects.

## Install

    

## Run the app

    java -cp dsmdb.jar

## Run the tests

    

# REST API

The REST API to the Simple Movie Search Engine app is described below.

## Get Movie

### Request

`GET /movies/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/movies/25

### Response

    

## Create a new Movie

### Request

`POST /movies/`

    curl -X POST "http://localhost:8080/movies/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"audienceScore\": 0, \"genre\": \"string\", \"id\": 0, \"leadStudio\": \"string\", \"name\": \"string\", \"profitability\": 0, \"rottenTomatoesScore\": 0, \"worldwideGross\": 0, \"year\": 0}"

### Response

    
