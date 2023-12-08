package uk.co.greenthistle.coroutinespybeanexample

import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ExampleService(var exampleRepository: ExampleRepository) {
  suspend fun createVisitMapping(request: ExampleDto) =
    with(request) {
      exampleRepository.save(VisitId(nomisId, vsipId, label, MappingType.valueOf(mappingType)))
    }

  suspend fun getVisitMappingGivenNomisId(nomisId: Long): ExampleDto =
    exampleRepository.findById(nomisId)
      ?.let { ExampleDto(it) }
      ?: throw RuntimeException("NOMIS visit id=$nomisId")
}
