package org.coomber.scala.jersey.example

import java.util.UUID

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.ws.rs.core.Context
import javax.ws.rs.core.{UriInfo, Response}
import javax.ws.rs.{Path, POST, GET}

// TODO: Add entity store
// TODO: Support GET method
@Path("/dummy")
@Component
class RecorderResource {

  val log = LoggerFactory.getLogger(getClass)  
  
  @Context var uriInfo: UriInfo = null
  
  @POST
  def createResource = {
    log.info("Created dummy")        
    val uri = uriInfo.getAbsolutePathBuilder().path("DUMMY_STRING").build();
    Response.created(uri).build();
  }
}
