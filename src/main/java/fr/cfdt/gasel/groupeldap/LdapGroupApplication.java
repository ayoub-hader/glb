package fr.cfdt.gasel.groupeldap;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class LdapGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapGroupApplication.class, args);
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService("http://172.20.16.4:8445/cas-gasel/services/j_acegi_cas_security_check");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	@Primary
	public AuthenticationEntryPoint authenticationEntryPoint(
			ServiceProperties sP) {
		CasAuthenticationEntryPoint entryPoint
				= new CasAuthenticationEntryPoint();
		entryPoint.setLoginUrl("http://172.20.16.4:8445/cas-gasel/login");
		entryPoint.setServiceProperties(sP);
		return entryPoint;
	}

	@Bean
	public TicketValidator ticketValidator() {
		return new Cas30ServiceTicketValidator(
				"http://172.20.16.4:8445/cas-gasel");
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider provider = new CasAuthenticationProvider();
		provider.setServiceProperties(serviceProperties());
		provider.setTicketValidator(ticketValidator());
		provider.setUserDetailsService(
				s -> new User("casuser", "Mellon", true, true, true, true,
						AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
		provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
		return provider;
	}

}

