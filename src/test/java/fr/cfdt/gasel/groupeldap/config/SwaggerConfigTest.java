package fr.cfdt.gasel.groupeldap.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springfox.documentation.swagger.web.SecurityConfiguration;


/**
 * SwaggerConfig Tester.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
public class SwaggerConfigTest {

    /**
     * The Swagger config.
     */
    SwaggerConfig swaggerConfig;

    /**
     * Test config api.
     */
    @Test
    public void testConfigApi() {
        swaggerConfig = new SwaggerConfig();
        Assert.assertNotNull(swaggerConfig.api());
    }
}
