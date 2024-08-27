import { defineConfig } from "orval";

export default defineConfig({
  petstore: {
    input: "./generated/api.json",
    output: {
      mode: 'tags-split',
      target: './',
      schemas: './schemas',
      workspace: 'src/spring-api',
      client: 'react-query',
      httpClient: "fetch",
      baseUrl: "http://localhost:8080",
    },
    hooks: {
      afterAllFilesWrite: "prettier --write",
    },
  },
});
