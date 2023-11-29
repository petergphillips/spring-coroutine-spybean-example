package uk.co.greenthistle.coroutinespybeanexample

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExampleDto(

  val nomisId: Long,

  val vsipId: String,

  val label: String? = null,

  val mappingType: String,
)
