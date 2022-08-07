# candlestick

## Deploy using Docker-Compose
In the root directory execute the following command to deploy the application along with the database

    docker-compose up
    
 After deployment the app Swagger UI can be accessed at http://localhost:8080/swagger-ui/index.html
 
 Or you can directly access the candlesticks for a stock in the browser at http://localhost:8080/v1/candlestick/AAPL/MINUTE
 
 The docker image is pushed here
 https://github.com/users/esafzay/packages/container/package/candlestick
 
 ### Note 1
 Since both the app and Postgres are running inside a container, the performance is lagging compared to deployment on the actual machine.
 
 ## Deploy without Docker
 To locally deploy the application without Docker, first install and run Postgres on your machine.
 
 Then on the command line to deploy the application, execute the following command
 
     ./mvnw clean spring-boot:run
     
  ### Note 2
  This is a very basic but working application, with no serious consideration for scalability and high load. To achive those requirements we would need to   make use of Postgres partitioning especially for the tick table, and also use batching while inserting ticks into the database.
