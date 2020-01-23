# REST API Simple Movie Search Engine

This is a Simple Movie search engine REST API used for retrieving movie information from MongoDB database.

The Application uses content from `movies.csv` file to populate Database with Movie objects. The CSV file is located at `src/main/resources`.
- To change The database url, port, name etc. navigate to `dsmdb\src\main\resources\` and edit `application.properties` file.

## Installation Requirements


### Install MongoDB server
Download MongoDB server installation file from [here](https://www.mongodb.com/download-center/community)


### Install the jar package using maven
navigate to folder where source is located and run the command below
 
    mvn package 

## Run the app

    java -cp /full_path_to_jar/dsmdb-0.0.1-SNAPSHOT.jar  

You can find REST Api endpoint at `http://localhost:8080/movies`.

Swagger url: `http://localhost:8080/swagger-ui.html`

# REST API

The REST API services of Simple Movie Search Engine app are described below.

## Get Movie

### Request

`GET /movies/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/movies/29

### Response

    {
      "id": 29,
      "name": "Remember Me",
      "genre": "Drama",
      "leadStudio": "Summit",
      "audienceScore": 70,
      "profitability": 3.49125,
      "rottenTomatoesScore": 28,
      "worldwideGross": {
        "currency": "USD",
        "amount": 55.86
      },
      "year": 2010
    }

## Create a new Movie

### Request

`POST /movies/`

    curl -X POST "http://localhost:8080/movies/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"audienceScore\": 99, \"genre\": \"Example\", \"id\": 0, \"leadStudio\": \"Example Studio\", \"name\": \"Test Movie\", \"profitability\": 100.54, \"rottenTomatoesScore\": 10, \"worldwideGross\": { \"amount\": 52.33, \"currency\": \"USD\" }, \"year\": 2020}"

### Response
    {
      "id": 78,
      "name": "Test Movie",
      "genre": "Example",
      "leadStudio": "Example Studio",
      "audienceScore": 99,
      "profitability": 100.54,
      "rottenTomatoesScore": 10,
      "worldwideGross": {
        "currency": "USD",
        "amount": 52.33
      },
      "year": 2020
    }


## Update an existing movie by ID

### Request
`POST /movies/{id}`

    curl -X PUT "http://localhost:8080/movies/45" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"Marley and Me - TEST\", \"genre\": \"Comedy\", \"leadStudio\": \"Fox\", \"audienceScore\": 77, \"profitability\": 3.746781818, \"rottenTomatoesScore\": 63, \"worldwideGross\": { \"currency\": \"USD\", \"amount\": 206.07 }, \"year\": 2008}"

### Response
    {
      "id": 45,
      "name": "Marley and Me - TEST",
      "genre": "Comedy",
      "leadStudio": "Fox",
      "audienceScore": 77,
      "profitability": 3.746781818,
      "rottenTomatoesScore": 63,
      "worldwideGross": {
        "currency": "USD",
        "amount": 206.07
      },
      "year": 2008
    }

## Delete Movie by ID

### Request
`DELETE /movies/{id}`

    curl -X DELETE "http://localhost:8080/movies/14" -H "accept: */*"

### Response
    {
      "deleted": true
    }

## Retrieve Movie fields by given ID and list of fields

### Request
`GET /movies/{id}/{fieldValues}`

    curl -X GET "http://localhost:8080/movies/12/worldwideGross%2C%20name%2C%20genre%2C%20id" -H "accept: */*"

### Response
    {
      "name": "Twilight: Breaking Dawn",
      "genre": "Romance",
      "id": 12,
      "worldwideGross": {
        "currency": "USD",
        "amount": 702.17
      }
    }
