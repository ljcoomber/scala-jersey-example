package org.coomber.scala.jersey.example

import org.junit.Test
import org.junit.Assert._
import com.sun.jersey.api.client.ClientResponse
import java.net.URI
import scala.util.matching.Regex
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests

@ContextConfiguration(locations=Array("/applicationContext.xml"))
class DummyResourceTest extends AbstractJUnit4SpringContextTests with JerseyInMemoryContainer {
  
  @Test
  def createDummyResource() {
    val response = resource.path("/dummy").post(classOf[ClientResponse])
    assertEquals(201, response.getStatus())
    val locations = response.getHeaders().get("Location")
    val uri = new URI(locations.get(0))
    
    Console.println("URI=" + uri)
    
    // TODO: Pick rest from application context
    // TODO: Add entity store or equivalent for testing
    //val MatchRe = """/recorder/(.*)""".r
    //val MatchRe(uuid) = uri.getPath
    //UUID.fromString(uuid)	// throws exception if invalid format
  }
}
