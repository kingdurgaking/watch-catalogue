# watch-catalogue
spring boot catalogue project


## Pre-requisite
 - **maven**
 - **java 8 atleast**
 
## Set up
 Application can be cloned on any directory using git command.
 - set JAVA_HOME
 - set MVN_HOME
 
 or 
 - set PATH in windows
 - export PATH in unix
 
 ### Other Application parameters
  under resource folder we have watches.json, 
  where you can modify the data.
   
 - WatchCatalogueApplication.java is the main spring boot class
 - Database used is h2 the configuration can be found in application.properties under resource folder
 - Applcation has standard folder structure 
 1. "service" under service
 2. "controller" under controller
 3. "repository" under dao
 4. "entity" under entity
 
 ## Test case
 run command "mvn test"
 
 ## Run Application
 
 run command "mvn exec:java"
 
 ## Swagger URL
 Incase you are running the application in localhost
http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config