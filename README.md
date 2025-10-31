# Intervals.icu MCP Server

This project provides a Model-Context-Protocol (MCP) server for interacting with the [intervals.icu](https://intervals.icu) API. It exposes a comprehensive set of tools that allow you to manage athletes, activities, events, and more.

## Prerequisites

- Java 21
- Maven

## Getting Started

1.  **Build the application:**
    ```bash
    ./mvnw clean install
    ```

2.  **Run the application:**
    ```bash
    java -jar target/intervals-mcp-*.jar
    ```

The server will start on port 8080 by default. Currenty no security layer is appled.

## Configuration

The application requires the following environment variables to be set:

- `INTERVALS_API_TOKEN`: Your API token for `intervals.icu`. You can obtain this from your account settings on the `intervals.icu` website.
- `INTERVALS_API_ATHLETE_ID`: Your athlete ID for `intervals.icu`.

You can set these variables in your environment or in a `.env` file.

**Example `.env` file:**
```
INTERVALS_API_TOKEN=your_api_token
INTERVALS_API_ATHLETE_ID=your_athlete_id
```

## Available Tools

This MCP server provides a wide range of tools for interacting with the `intervals.icu` API. The tools can be broadly categorized as follows:

- **Athlete Management:** Tools for fetching and updating athlete data, including profiles, settings, and training plans.
- **Activity Management:** Tools for creating, retrieving, updating, and deleting activities. You can also download activity files and analyze activity data such as power curves and heart rate data.
- **Event Management:** Tools for managing calendar events, including creating, updating, and deleting workouts, notes, and races.
- **Workout Management:** Tools for creating, retrieving, and managing workouts in your library.
- **Data Analysis:** Tools for fetching and analyzing various data points, including wellness records, fitness models, and performance metrics.

For a complete list of available tools and their descriptions, please refer to the `IntervalsToolProvider.java` file.
