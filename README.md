# Spring Boot REST API with H2 Database

A small example in the form of a note-taking application built using Spring Boot for the REST API with H2 for a database. Demonstrates elementary CRUD operations in response to HTTP as well as returning JSON.

#### !!! TODO:  FULL TESTS !!!

## Getting Started


### Installing
Make sure to edit your ```application.properties``` and change the database information to reflect where you would like H2 to create the DB file.
It is currently set to the directly from which you are compiling the application.
```sh
spring.datasource.url=jdbc:h2:file:./notesDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=berti
spring.datasource.password=
```
To test out this application, you need Maven to build the dependencies.

- First, install the dependencies

```sh
mvn clean install
```
### Running
- Second, run the production build with live reload
```sh
mvn spring-boot:run
```
When the application is first built, it will create a database file in the directory specified in the ```application.properties``` file. 

## * Testing *

### Maven Tests
```
mvn test
```
### Curl Tests

The hotels API lives at the route ```/api/hotels```. If your application is running on localhost:8080, you would access the API via http://localhost:8080/api/hotels.

To create a new note, post a JSON payload to the API endpoint as modeled below:
```curl
POST /api/hotels
BODY a note
```
Returns: a saved hotel...
Example
```curl
curl -i -H "Content-Type: application/json" -X POST -d '{"name" : "Blue Residency", "lat" : 12, "lon" : 13}' http://localhost:8080/api/hotels
```
Returns:
```1
```

Which is the number of affected rows
Get a note using an API call:
```
GET /api/hotels/{id}
```
Returns: the requested hotel of given id..
Example:
```curl
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/hotels/1
```
Returns:
```json
{
 "id" : 1,
 "name" : "Blue Residency",
 "lat" : 12,
 "lon" : 13
}
```
I can get all hotels using an API call:
```
GET /api/hotels
```
Returns: A list of hotels in db

Example:

```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/hotels
```
Returns:
```json
[
 {
 "id" : 2,
 "name" : "Sapphire",
 "lat" : 18,
 "lon" : 19
 },
 {
 "id" : 1,
 "name" : "Blue Residency",
 "lat" : 12,
 "lon" : 13
 }
]
```
You can also serach hotels with their names using regex
To search hotels by their names, use the 'query' parameter in the GET request
Example:
```curl
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/hotels?query=Sapph
```
Returns a list of every hotel with the word 'Sapph' in it.

To delete an individual note use the endpoint ```/api/hotels/``` with a parameter of 'id' signifying the note you wish to remove.
```curl
DELETE /api/notes
ID int value of id to delete
```
Returns: the number of affected rows
Example
```curl
curl -i -H "Content-Type: application/json" -X POST  http://localhost:8080/api/hotels/delete/1
```

## Built With


* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Quick start Spring Framework web applications
* [H2 Database Engine](https://h2database.com/) - the Java SQL database
* [JSON.simple](https://github.com/fangyidong/json-simple) -JSON.simple is a simple Java toolkit for JSON



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

