package com.HepatoPath.HepatoPath.Admin.Configuration;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomCatConfig
{
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            System.out.println("Tomcat maxSwallowSize: " + connector.getProperty("maxSwallowSize"));
            System.out.println("Tomcat maxPostSize: " + connector.getMaxPostSize());
            System.out.println("Tomcat maxHttpHeaderSize: " + connector.getProperty("maxHttpHeaderSize"));
        });
    }
}
