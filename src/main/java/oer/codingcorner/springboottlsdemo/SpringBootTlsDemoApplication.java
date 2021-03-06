package oer.codingcorner.springboottlsdemo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootTlsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTlsDemoApplication.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerFactory createServletContainer() {
		TomcatEmbeddedServletContainerFactory tomcatContainer = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                constraint.addCollection(securityCollection);                
                context.addConstraint(constraint);
			}			
		};
		tomcatContainer.addAdditionalTomcatConnectors(redirectConnectorHttp());
		return tomcatContainer;	
	}

	private Connector redirectConnectorHttp() {
		Connector redirectConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		redirectConnector.setScheme("http");
		redirectConnector.setPort(8080);
		redirectConnector.setSecure(false);
		redirectConnector.setRedirectPort(8443);
		return redirectConnector;
	}
}
