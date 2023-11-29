package uk.co.greenthistle.coroutinespybeanexample

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
class ExampleResource(private val mappingService: ExampleService) {

  @PostMapping("/mapping/visits")
  @ResponseStatus(HttpStatus.CREATED)
  suspend fun createMapping(
    @RequestBody createMappingRequest: ExampleDto,
  ) = mappingService.createVisitMapping(createMappingRequest)

  @GetMapping("/mapping/visits/nomisId/{nomisId}")
  @ResponseStatus(HttpStatus.OK)
  suspend fun getVisitMappingGivenNomisId(
    @PathVariable nomisId: Long,
  ): ExampleDto = mappingService.getVisitMappingGivenNomisId(nomisId)
}
