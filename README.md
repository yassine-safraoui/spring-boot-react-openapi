# Overview

This project illustrates how to use a spring boot API as a backend for a React frontend app while ensuring typesafe API calls and avoiding the need to write boilerplate code for API calls.

# Technologies used

The project uses the following technologies:

- Kotlin
- Spring Boot
- React
- Typescript
- [Vite](https://github.com/vitejs/vite)
- [Tailwind CSS](https://github.com/tailwindlabs/tailwindcss)
- [@tanstack/react-query](https://github.com/tanstack/query)
- [Orval](https://github.com/orval-labs/orval)

# How to run the project

To run the project in development mode, you first need to make sure that the database environment variables DB_URL, DB_USERNAME and DB_PASSWORD are defined. Then you can follow the steps below:

1. Start the spring boot server using the gradle command `./gradlew bootRun`
2. For kotlin hot reloading, keep this command running in a separate terminal `./gradlew compileKotlin --continuous --parallel --build-cache --configuration-cache`
3. Start the react app located in `src/main/frontend` using the npm command `npm dev`
4. Whenever you make changes to the spring boot api, run the command `./gradlew generateOpenApiDocs` to generate the openapi documentation of the api in src/main/frontend/generated
5. Start this command that will generate the typescript types corresponding to spring boot api whenever the api specification changes: `npm run watch-api`
