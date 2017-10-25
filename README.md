# spring-boot-mvc-test
A Test Spring Boot application that uses Spring MVC Controllers and is unit tested.

There's also an example of populating a spring bean with values from a properties file (`AppPropertiesContainer`).

![unit test result badge](https://travis-ci.org/stealthrabbi/spring-boot-mvc-test.svg?branch=master)

## Build and Test

* To build and test, from the root of the repository, run `./gradlew build`.
* To run the web application, run `./gradlew bootRun`. Open web browser to:
  * http://localhost:8080 -- view a basic intro page
  * http://localhost:8080/students/list -- to view JSON list of users
  * http://localhost:8080/students/Steve -- to view JSON for a specific user


## Dependencies
All dependencies are managed in the `build.gradle` file.
  
## Explanation of Code

This is a Spring Boot application.
AppBootMain contains the main function that builds the application as a Spring Boot web app.

The `domain_model` package has a simple POJO class that gets returned by the Controller.

The `data_repository` package has a simple Repository class and interface representing a data store. The concreate class just contains a static list of objects. The interface exists so the dependency can be mocked using Mockito.

The `web_controllers` package contains Spring MVC Controllers that map REST URIs to methods that return objects.

View the `test` package to see Spring Tests for the Controllers.
`AbstractSpringTest` is a simple base class to be used for all unit tests for spring managed classes.
`AbstractControllerTest` sets up a MockMvc to invoke calls on the REST interface.
`StudentControllerTest` tests the `StudentController` Controller. It creates a mockito mock for the `StudentRepository` and injects it in to the controller. MockMvc is used to invoke calls on the REST interface and check returned JSON values.



# angular 4 app generated via http://www.umeshmorsu.com/2017/04/28/spring-boot-and-angular-cli-example/