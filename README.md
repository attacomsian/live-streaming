# Live Streaming API
REST API for uploading video media, and then for listing and streaming the videos. Login is required.

## Usage
In order to build this application, run the following Gradle command:
```ssh
./gradlew build
```

To run this application, use the following Gradle command:
```ssh
./gradlew bootRun
```

## Usage (Docker)
In order to use the Docker version of the app, it is needed docker-compose.
To build the application, run the following command:
```bash
live-streaming$ make build
```
To run the containers (both the application and the MySQL database), run the following command:
```bash
live-streaming$ make up
```
To stop the containers, use the following command:
```bash
live-streaming$ make down
```

## Documentation
When the application is running, the documentation is available at the following link:
```text
http://localhost:8080/swagger-ui.html
```

## License
MIT
