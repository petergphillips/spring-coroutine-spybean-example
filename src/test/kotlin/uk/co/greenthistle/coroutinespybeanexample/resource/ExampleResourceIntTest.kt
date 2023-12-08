package uk.co.greenthistle.coroutinespybeanexample.resource

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.AdditionalAnswers
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.check
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.kotlin.spy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import uk.co.greenthistle.coroutinespybeanexample.ExampleDto
import uk.co.greenthistle.coroutinespybeanexample.ExampleRepository
import uk.co.greenthistle.coroutinespybeanexample.MappingType
import uk.co.greenthistle.coroutinespybeanexample.VisitId
import uk.co.greenthistle.coroutinespybeanexample.ExampleService

@AutoConfigureWebTestClient(timeout = "PT60M")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ExampleResourceIntTest {
  @Autowired
  lateinit var webTestClient: WebTestClient

  // comment out spybean here to get test to pass
  @Autowired
  lateinit var exampleRepository: ExampleRepository

  @Autowired
  lateinit var exampleService: ExampleService

  lateinit var repositorySpy: ExampleRepository

  @BeforeEach
  fun setup() {
    repositorySpy = mock(defaultAnswer = AdditionalAnswers.delegatesTo(exampleRepository))
    exampleService.exampleRepository = repositorySpy
  }

  @Test
  fun `create mapping success`() {
    webTestClient.post().uri("/mapping/visits")
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        BodyInserters.fromValue(
          """{
            "nomisId"     : 1234,
            "vsipId"      : "12345678",
            "label"       : "2022-01-01",
            "mappingType" : "ONLINE"
          }""",
        ),
      )
      .exchange()
      .expectStatus().isCreated

    runBlocking {
      // This shows that we can spy on the bean
      verify(repositorySpy).save(
        check {
          assertThat(it.nomisId).isEqualTo(1234)
        }
      )
    }
  }

  @Test
  fun `get mapping success`() {
    runBlocking {
      // and also mock results that aren't in the database
      whenever(repositorySpy.findById(any())) .thenReturn(VisitId(
        nomisId = 2345,
        vsipId = "ABCD",
        label = "2022-01-01",
        mappingType = MappingType.MIGRATED,
      ))
    }

    val mapping = webTestClient.get().uri("/mapping/visits/nomisId/2345")
      .exchange()
      .expectStatus().isOk
      .expectBody(ExampleDto::class.java)
      .returnResult().responseBody!!

    assertThat(mapping.nomisId).isEqualTo(2345L)
    assertThat(mapping.vsipId).isEqualTo("ABCD")
    assertThat(mapping.label).isEqualTo("2022-01-01")
    assertThat(mapping.mappingType).isEqualTo("MIGRATED")
  }
}
