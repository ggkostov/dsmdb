# REST API Simple Movie Search Engine

This is a Simple Movie search engine REST API used for retrieving movie information from MongoDB database.

The Application uses content from `movies.csv` file to populate Database with Movie objects. The CSV file is located at `src/main/resources`.

## Installation Requirements

- Maven required - [link](https://maven.apache.org/index.html)

###Install MongoDB server
Download MongoDB server installation file from [here](https://www.mongodb.com/download-center/community)

###Install the jar package using maven
navigate to folder where source is located and run the command below
 
    mvn package 

## Run the app

    java -cp /full_path_to_jar/dsmdb-0.0.1-SNAPSHOT.jar  

You can find REST Api endpoint at `http://localhost:8080/movies`.

Swagger url: `http://localhost:8080/swagger-ui.html`

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
    
## Retrieve Movie fields by given ID and list of fields

### Request
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
    
### Reference
for more REST  examples please review the swagger UI - `http://localhost:8080/swagger-ui.html`.