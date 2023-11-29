package uk.co.greenthistle.coroutinespybeanexample.jpa.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import uk.co.greenthistle.coroutinespybeanexample.jpa.VisitId

@Repository
interface ExampleRepository : CoroutineCrudRepository<VisitId, Long>
