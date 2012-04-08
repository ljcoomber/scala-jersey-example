package org.coomber.scala.jersey.example

import java.net.URI
import scala.collection.JavaConversions.asScalaSet
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.sun.jersey.api.client.config.DefaultClientConfig
import com.sun.jersey.api.client.Client
import com.sun.jersey.spi.container.WebApplicationFactory
import com.sun.jersey.spi.spring.container.SpringComponentProviderFactory
import com.sun.jersey.test.framework.impl.container.inmemory.TestResourceClientHandler
import com.sun.jersey.test.framework.spi.container.inmemory.InMemoryTestContainerFactory
import com.sun.jersey.test.framework.{AppDescriptor, LowLevelAppDescriptor}
import javax.ws.rs.core.UriBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import com.sun.jersey.test.framework.spi.container.TestContainer

/**
 * A factory for creating an in-memory container for testing Jersey applications
 */
class JerseyInMemoryContainerFactory(ctx: ConfigurableApplicationContext) extends InMemoryTestContainerFactory {

  override def create(baseUri: URI, ad: AppDescriptor): TestContainer = {
    new JerseyInMemoryTestContainer(baseUri, ad.asInstanceOf[LowLevelAppDescriptor])
  }

  class JerseyInMemoryTestContainer(srcBaseUri: URI, ad: LowLevelAppDescriptor) extends TestContainer {
    val log: Logger = LoggerFactory.getLogger(getClass)

    val baseUri = UriBuilder.fromUri(srcBaseUri).build()
    val resourceConfig = ad.getResourceConfig
    val webApp = WebApplicationFactory.createWebApplication()

    override def getBaseUri = baseUri

    override def getClient() = {
      val clientConfig = new DefaultClientConfig();
      val providerSingletons = resourceConfig.getProviderSingletons
      providerSingletons.map(clientConfig.getSingletons.add(_))

      new Client(new TestResourceClientHandler(baseUri, webApp), clientConfig)
    }

    override def start() {
      if(!webApp.isInitiated()) {
        log.debug("Starting in memory container")        
        webApp.initiate(resourceConfig, new SpringComponentProviderFactory(resourceConfig, ctx))
      }
    }

     override def stop() {
      if(webApp.isInitiated()) {
        log.debug("Stopping in memory container")
        webApp.destroy()
      }
    }
  }
}
