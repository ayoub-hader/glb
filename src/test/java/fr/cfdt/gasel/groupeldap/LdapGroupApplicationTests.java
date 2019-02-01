package fr.cfdt.gasel.groupeldap;

import fr.cfdt.gasel.groupeldap.properties.MessagesProperties;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

@ComponentScan({"fr.cfdt"})
@EnableConfigurationProperties(MessagesProperties.class)
public class LdapGroupApplicationTests {
	@Test
	public void main() {
		LdapGroupApplication.main(new String[] {});
	}
}

