package uk.co.greenthistle.coroutinespybeanexample.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uk.co.greenthistle.coroutinespybeanexample.data.ExampleDto
import uk.co.greenthistle.coroutinespybeanexample.jpa.repository.ExampleRepository

@Service
@Transactional(readOnly = true)
class ExampleService(
  private val exampleRepository: ExampleRepository,
) {
  @Transactional
  suspend fun createVisitMapping(createMappingRequest: ExampleDto) =
    exampleRepository.findById(createMappingRequest.nomisId)
}
