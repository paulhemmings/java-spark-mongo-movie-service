## Movie Client / Service implementation

#### Prerequisites.

* Requires Gradle
* Requires MongoDB

#### How to use the application

##### Clone

````
$ git clone https://github.com/paulhemmings/java-spark-mongo-movie-service.git
````

##### Build

````
$ cd java-spark-mongo-movie-service
$ gradle wrapper
$ ./gradlew clean build
````

##### Run unit tests

````
$ ./gradlew test
````

##### Install MongoDB

````
$ brew update
$ brew install mongodb
````

##### Run MongoDB daemon

````
$ mongod --config /usr/local/etc/mongod.conf
````

##### Start web service

````
$ ./gradlew run
````

## Services
##### Show all movies

GET :: http://localhost:4567/movies

##### Show single movie

GET :: http://localhost:4567/movie/[id]

##### Upload movie

PUT :: http://localhost:4567/movie

## Client
###### View the movies

http://localhost:4567

###### Add new movies

http://localhost:4567/#/upload

###### Example movie payload

The upload process takes a JSON payload. If uploaded successfully the text area will display the stored movie document.

````
{
    "movie_name" : "Avengers - Age of Ultron",
    "image_url" : "http://resizing.flixster.com/s8kIQtOhr36lGPkcUGCVeqVWw9Y=/180x270/dkpu1ddg7pbsk.cloudfront.net/movie/11/19/01/11190143_ori.jpg",
    "rating" : "4.5",
    "description" : "When Tony Stark jumpstarts a dormant peacekeeping program, things go awry and Earth's Mightiest Heroes, including Iron Man, Captain America, Thor, The Incredible Hulk, Black Widow and Hawkeye, are put to the ultimate test as they battle to save the planet from destruction at the hands of the villainous Ultron."
}
````
