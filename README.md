# Spring Boot coroutine with spybean example

Example project demonstrating https://github.com/springdoc/springdoc-openapi/issues/

Sample controller can be found at OptionalController

Failing test can be found at ExampleControllerTest

Downgrading spring boot in build.gradle.kts to `3.1.5` or removing the `@SpyBean` fixes the test.

