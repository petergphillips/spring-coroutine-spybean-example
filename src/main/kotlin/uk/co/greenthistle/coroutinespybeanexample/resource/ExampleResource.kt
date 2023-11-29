package uk.co.greenthistle.coroutinespybeanexample.resource

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import uk.co.greenthistle.coroutinespybeanexample.data.ExampleDto
import uk.co.greenthistle.coroutinespybeanexample.service.ExampleService

@RestController
@Validated
@RequestMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
class ExampleResource(private val mappingService: ExampleService) {

  @PostMapping("/mapping/visits")
  @ResponseStatus(HttpStatus.CREATED)
  suspend fun createMapping(
    @RequestBody
    createMappingRequest: ExampleDto,
  ) = mappingService.createVisitMapping(createMappingRequest)
}
