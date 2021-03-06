package org.wildfly.swarm.examples.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Simple set of tests for the HelloWorld Web Service to demonstrate accessing the web service using a client
 *
 * @author lnewson@redhat.com
 * @author Yoshimasa Tanabe
 *
 */
public class ClientIT {

    /**
     * The path of the WSDL endpoint in relation to the deployed web application.
     */
    private static final String WSDL_PATH = "HelloWorldService?wsdl";

    private HelloWorldService client;

    @Before
    public void setup() {
        try {
            client = new Client("http://localhost:8080/" + WSDL_PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHello() {
        // Get a response from the WebService
        final String response = client.sayHello();
        assertEquals(response, "Hello World!");
    }

    @Test
    public void testHelloName() {
        // Get a response from the WebService
        final String response = client.sayHelloToName("John");
        assertEquals(response, "Hello John!");
    }

    @Test
    public void testHelloNames() {
        // Create the array of names for the WebService to say hello to.
        final List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        names.add("Mark");

        // Get a response from the WebService
        final String response = client.sayHelloToNames(names);
        assertEquals(response, "Hello John, Mary & Mark!");
    }

}