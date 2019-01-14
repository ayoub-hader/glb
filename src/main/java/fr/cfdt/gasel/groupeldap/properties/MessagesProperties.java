package fr.cfdt.gasel.groupeldap.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "error.ws")
@PropertySource("classpath:messages.properties")
@Getter
@Setter
public class MessagesProperties {
    private String technicalExceptionInGetLdapUsersByNpas;
}

