package uk.co.greenthistle.coroutinespybeanexample.resource

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import uk.co.greenthistle.coroutinespybeanexample.jpa.repository.ExampleRepository

private const val nomisId = 1234L
private const val vsipId = "12345678"

@AutoConfigureWebTestClient(timeout = "PT60M")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ExampleResourceIntTest {
  @Autowired
  lateinit var webTestClient: WebTestClient

  @SpyBean(name = "visitIdRepository")
  lateinit var exampleRepository: ExampleRepository

  @Test
  fun `create mapping success`() {
    webTestClient.post().uri("/mapping/visits")
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        BodyInserters.fromValue(
          """{
            "nomisId"     : $nomisId,
            "vsipId"      : "$vsipId",
            "label"       : "2022-01-01",
            "mappingType" : "ONLINE"
          }""",
        ),
      )
      .exchange()
      .expectStatus().isCreated
  }
}
