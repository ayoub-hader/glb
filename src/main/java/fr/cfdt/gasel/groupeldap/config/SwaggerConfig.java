package fr.cfdt.gasel.groupeldap.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger config.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

    private static final String PACKAGE_SWAGER_API_TO_BE_SCANNED = "fr.cfdt.gasel";

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        LOGGER.info("Configuring Swagger2 for documentation and Live API");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_SWAGER_API_TO_BE_SCANNED))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfo(
                "Demo Microservice",
                "",
                "Version 1.0-SNAPSHOT",
                "urn:tos",
                new Contact("Neoxia Tech Engineering",
                        "https://www.neoxia.com/",
                        ""),
                "",
                "",
                Collections.emptyList()
        );
    }

}
