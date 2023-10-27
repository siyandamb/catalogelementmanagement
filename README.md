# Catalog Element Management

Catalog Element Management is a Spring Boot microservice designed to manage and enforce the lifecycle status transitions of catalog elements, ensuring a well-defined workflow is followed.

## Features

- Provides RESTful API endpoints to manage catalog elements.
- Enforces the defined status transitions.
- Stores catalog elements in an H2 database.
- Includes unit tests for critical functionality.
- Follows best practices for Spring Boot applications.

## Prerequisites

Before you start, ensure you have the following requirements:

- Java Development Kit (JDK) 11 or higher
- Spring Boot 2.5+
- Maven or Gradle build tool
- Git for version control
- An integrated development environment (IDE)

## Usage

Detailed information on how to use the API endpoints is provided in the [API Endpoints](#api-endpoints) section.

## API Endpoints

- `PATCH /catalog-element/{id}/status`: Update the status of a catalog element.

Refer to the [CatalogElementController](src/main/java/com/example/catalogelementmanagement/controller/CatalogElementController.java) for more details on the API endpoints and their usage.

## Testing

The project includes unit tests to ensure the reliability of critical functionality. You can run these tests using the command `mvn test`.


