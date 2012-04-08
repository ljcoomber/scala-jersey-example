package org.coomber.scala.jersey.example

import com.sun.jersey.test.framework.spi.container.TestContainerFactory
import com.sun.jersey.test.framework.{JerseyTest, LowLevelAppDescriptor}
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.sun.jersey.test.framework.spi.container.TestContainer
import org.junit.Before
import org.junit.After

trait JerseyInMemoryContainer {

  var jerseyTest: JerseyTest = null
  
  val context = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml")
  
  @Before
  def setUp() {
    jerseyTest = new JerseyTest(new LowLevelAppDescriptor.Builder("org.coomber.scala.jersey.example").build()) {
      override def getTestContainerFactory = new JerseyInMemoryContainerFactory(context)
    };

    jerseyTest.setUp();
  }
  
  @After
  def tearDown() {
    jerseyTest.tearDown();
  }

  def resource = jerseyTest.resource

  def client = jerseyTest.client
  
  def getContext = context
}
