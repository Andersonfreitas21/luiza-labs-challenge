# Schedule API

API that receives a send schedule request from Communication.

### To compile and run the project you must have installed

- [Maven](https://maven.apache.org/) - Build automation tool primarily used in Java projects.
- [Docker](https://docs.docker.com/get-docker/) - Running container applications.

### Installation

- Clone the project: `$git clone https://github.com/Andersonfreitas21/luiza-labs-challenge.git`
- After completing the project download, access its directory: `$cd luiza-labs-challenge/`

#### Installing dependencies and running tests with Maven:

- To install the dependencies and run the tests, use the command: `$mvn clean package`
- Right after use the command: `$mvn clean install`

### Docker - Building the application

- Run the command `$docker build -t andersonfreitas21/schedule .` to build the application.
- And finally, run `$docker-compose up -d` to run the application.
- Adminer is a browser-accessible solution for administering MySQL and freely distributed as a PHP file/Docker image

`Agora você terá a aplicação rodando perfeitamente.`

### At APIs

You can view the APIs using Swagger through this URL: http://localhost:5000/swagger-ui.html#/

#### Descrição das APIs

- Create Scheduling Request - http://localhost:5000/api/v1/schedule?Type=SMS - Body to JSON

`{
  "addressee": {
    "addresseeName": "Anderson Freitas",
    "contactNumber": "(88)997128191",
    "email": "andersonfreitas21@gmail.com"
  },
  "message": "Message to be sent",
  "send_date": "2021-11-11 22:18:01"
}`

-`OBS: The Types available are SMS, EMAIL, PUSH e WHATSAPP`

- Search scheduling requests by status - http://localhost:5000/api/v1/schedule?StatusOfSchedule=PENDING -
  
- Statuses available for consultation are `PENDING, DELETED, SENT` . 
- The status search is paged with the [Spring's own pageable](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html)

- Delete a scheduling request by `UUID` - http://localhost:5000/api/v1/schedule/{uuid}
