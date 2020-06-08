# Demo Docker Application

This application was built to demonstrate a simple Docker application.

## Dockerfile

The dockerfile builds the current project and prepares an image. It requires a MongoDB instance running.
The host, username, and password can be changed at runtime using spring properties under application.properties.

## docker-compose.yml

The preferred method of running the application. This is a basic docker compose file that will get the application up
and running with the MongoDB instance.

## Running the application

1. git clone the application

2. Change into the cloned directory

3. Run `docker-compose up` to start the containers. Will take a little while

    * Alternatively, can run `docker-compose up -d` to run the compose in the background.

    * For monitoring, please use `docker-compose logs -f`

4. Navigate to `localhost:8080` to view the first page.

## Running the docker file

**PREREQUISITE**: Make sure a docker network exists of the name spring-network `docker network create spring-network`
This is required to ensure that both of the docker containers can talk with each other.

1. Run the below to launch a new mongo container with username, password, and database specified.

    * `docker container run -p 27017:27017 -d --name db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root -e MONGO_INITDB_DATABASE=admin --network=spring-network mongo`

2. Run `./mvnw package dockerfile:build` to prepare the image

3. Run `docker container run -p 8080:8080 -d --name spring --network=spring-network -d vkainth/demoapp` to run the docker image

4. Run `docker container logs -f spring` to monitor the logs

5. Navigate to `localhost:8080` to view the results

## Configuring the application

1. Update the mongo database URI in the application-docker.properties file (The application uses Spring Profiles for separation)

2. Update the mongo database information in step 1 of **Running the docker file** if updating step 1 above

## Pushing the docker image

1. Change `docker.image.prefix` in pom.xml

2. Run `./mvnw package dockerfile:build` to build the project

3. Run `docker login` to login to docker hub

4. Run `docker image ls` to view the image just built

5. Run `docker push <your-username>/<your-appname>` to push the image
