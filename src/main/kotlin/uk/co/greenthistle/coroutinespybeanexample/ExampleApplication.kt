package uk.co.greenthistle.coroutinespybeanexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {
  runApplication<ExampleApplication>(*args)
}
