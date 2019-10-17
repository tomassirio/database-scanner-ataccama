<H1>Ataccama's Database Scanner</H1>

<H3>Technologies used</H3>

- Java 8

- Spring boot

- MySql

- Git/Github

- Docker

The Spring Environment was created with `https://start.spring.io/`

<H3>How to run</H3>

Download the project as a Zip or Clone it.

 - Open a terminal
 
 - Type `./mvnw package`

 - Run `docker-compose run` and after that `docker-compose up`
 
 _If you are running this project on a linux machine, don't forget to run the projects as root._
 
 To open the app get on your favourite Browser and go to `http://localhost:8080/swagger-ui.html#/`
 
<H3>REST API</H3>

The followings urls are the endpoints created for this excercise with it's HTTP method next to it:

<H6>Instance-info-controller</H6>

- **GET** /instance_info

- **POST** /instance_info

- **PUT** /instance_info

- **GET** /instance_info/{id}

- **DELETE** /instance_info/{id}

<H6>scanner-controller</H6>

- **GET** /scanner/{id}/columns

- **GET** /scanner/{id}/database/columns

- **GET** /instance_info/{id}/database/rows

- **GET** /instance_info/{id}/database/tables

- **GET** /scanner/{id}/rows

- **GET** /scanner/{id}/schemas

- **GET** /scanner/{id}/tables

- **GET** /instance_info

<H6>statistics-controller</H6>

- **GET** /statistics/{id}/column

- **GET** /statistics/{id}/database/column

- **GET** /statistics/{id}/database/table

- **GET** /statistics/{id}/table

<H3>Considerations</H3>

 - The Scanner was developed as a synchronic service. 
 What i mean by this is that the Database structure is grabbed
 before the JSON is generated to be returned. 
 
 **PROS:**
  
    - It's development was easier than an Asynchronous scanner.
    - It's humanly readable
    - It could be developed in just two days
 
 **CONS:**
 
    - It doesn't support big databases since the JVM heap collapses before returning the data
    - It's a less scalable approach
    
- Every endpoint has a similar endpoint noted with /database/ which retrieves the data from the database 
appointed on the Instance to be runned. The regular endpoint doesn't care about the database parsed on the Instance
and brings the info on every schema the connection can offer.

- Bringing the upper point for the last time, the databases 'sys', 'information_schema', 'mysql' and 'performance_schema'
are not considered on the scan since they are automatically generated, thus, barely important :P

- One of the assignments on the Bonus point was to bring the MEDIAN on every column. As mysql doesn't offer a median
method (such like it's min, max and avg counterparts) and after an hour long trying to get the query right, I decided to give up
on it.

- The min(), max() and avg() are little 'quirky' since i didn't filter the columns by numeric types. Same goes
for min and max. If a column is of varchar type, you might get two different texts on both min and max results. 
With a little bit of investigation i found out that these functions take the VARCHAR alphabetical order as a value,
so the results are correct.

- For each new feature added to the scanner, a branch on github was generated with a nomenclature in the ways of `feature/*`.
After the feature was functioning, a merge to the branch `develop` was issued. The `master` branch is used as a production of sorts
branch.

- As a side note, i really enjoyed this interview process so far and can say for sure that i learned a lot practicing. So
whatever your decision on the process is, i can only show my gratitude towards you :)

<H3>To Do:</H3>
 
 ~~- Develop a field query~~
 
 ~~- Develop the Bonus~~ 
 
 ~~- Create some custom exceptions~~
 
 ~~- Make the response entities to throw a 400 or 500 when the params are not ok~~
 
 ~~- Insert a logger on the different services to retrieve info while scanning~~
 
 - Make Tests
 
 ~~- Dockerize the app~~ 
 
 ~~- Document everything on this Readme~~ 
 
 - Check that everything is working as intended
 
 - Send it

<H3>Who am I?</H3>

Tomas Sirio

Backend Java Dev

Argentina

tomassirio@gmail.com
