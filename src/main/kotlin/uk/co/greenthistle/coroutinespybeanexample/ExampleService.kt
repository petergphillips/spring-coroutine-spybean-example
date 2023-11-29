package uk.co.greenthistle.coroutinespybeanexample

import org.springframework.stereotype.Service

@Service
class ExampleService(val exampleRepository: ExampleRepository) {
  suspend fun createVisitMapping(createMappingRequest: ExampleDto) =
    exampleRepository.findById(createMappingRequest.nomisId)
}
