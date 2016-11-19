package org.thyanesh.assessment.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thyanesh.paypal.assessment.Application;
import org.thyanesh.paypal.assessment.client.RestServiceClient;
import org.thyanesh.paypal.assessment.service.IRestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ServiceTestClient {

    private static final Logger LOG = Logger.getLogger(ServiceTestClient.class);
    
    ApplicationContext applicationContext;
    IRestService client;

    @Before
    public void setUp() throws IOException {
        client = new RestServiceClient();
    }

    @Test
    public void testSave() {
    	String inputString = "test";
    	String result = client.save(inputString);
    	assertNotNull("Result is null", result);
        assertTrue("Result is empty", !result.isEmpty());
    }

    @Test
    public void testLookup() throws IOException {
    	String stringId = "780";
		String result = client.lookUp(stringId); 
    	assertNotNull("Result is null", result);
        assertTrue("Result is empty", !result.isEmpty());
    }

}
