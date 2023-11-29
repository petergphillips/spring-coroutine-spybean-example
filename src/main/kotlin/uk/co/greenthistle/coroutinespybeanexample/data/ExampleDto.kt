package uk.co.greenthistle.coroutinespybeanexample.data

import com.fasterxml.jackson.annotation.JsonInclude
import uk.co.greenthistle.coroutinespybeanexample.jpa.VisitId
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExampleDto(

  val nomisId: Long,

  val vsipId: String,

  val label: String? = null,

  val mappingType: String,

  val whenCreated: LocalDateTime? = null,
) {
  constructor(visitId: VisitId) : this(
    nomisId = visitId.nomisId,
    vsipId = visitId.vsipId,
    label = visitId.label,
    mappingType = visitId.mappingType.name,
    whenCreated = visitId.whenCreated,
  )
}
