# Spring Boot coroutine with spybean example

Example project demonstrating https://github.com/spring-projects/spring-framework/issues/31713

Sample controller can be found at [ExampleResource](https://github.com/petergphillips/spring-coroutine-spybean-example/blob/main/src/main/kotlin/uk/co/greenthistle/coroutinespybeanexample/ExampleResource.kt)

Failing test can be found at [ExampleResourceIntTest](https://github.com/petergphillips/spring-coroutine-spybean-example/blob/main/src/test/kotlin/uk/co/greenthistle/coroutinespybeanexample/resource/ExampleResourceIntTest.kt)

Downgrading spring boot in build.gradle.kts to `3.1.5` or removing the `@SpyBean` fixes the test.

