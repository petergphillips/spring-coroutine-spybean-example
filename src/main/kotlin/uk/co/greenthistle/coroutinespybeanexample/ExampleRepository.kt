package uk.co.greenthistle.coroutinespybeanexample

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository : CoroutineCrudRepository<VisitId, Long>
