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
