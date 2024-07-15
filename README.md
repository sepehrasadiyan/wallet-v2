# Project Overview

In this project, I utilize multiple design patterns to achieve optimal readability and scalability. 

### Architecture

- **CQRS with Event Sourcing**: For transaction handling, I employ CQRS with event sourcing to clearly separate commands and queries.
- **Command Design Pattern**: I leverage the command design pattern alongside the builder pattern to construct precise commands.

### Technology Stack

- **Backend**: MySQL is used for the database.
- **Integration Testing**: I implement integration tests using Testcontainers to fully simulate a real-world environment.

## Requirements for Running the App

1. **Docker & Docker Compose**: Ensure you have Docker and Docker Compose installed on your machine.
2. **Start the Application**: Run the command `./start.sh` to launch the application easily.
3. **Stop the Application**: Use `./stop.sh` to shut down the application.
4. **View Logs**: To trace the application logs, use the command `docker compose logs -f spring-boot-app`.

### Data Storage

All data logs and MySQL data are stored under the path `/home/madara/data/(logs or mysql)`. Feel free to change this path as needed.

## API Endpoints

- **Deposit API**: `POST /api/v1/command/deposit`
- **Query API**: `GET /api/v1/query/{userId}` (e.g., `/api/v1/query/1`)

### Attention

There are two default user IDs:
- **ID 1**: Enabled user
- **ID 12**: Suspended user
