package com.clementbayard.clement_ws;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Base64;
@SpringBootConfiguration
@SpringBootApplication
public class ClementWsApplication {

    private String allowedUrl="http://localhost:4200";
    static Logger logger= LogManager.getLogger(ClementWsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ClementWsApplication.class, args);
        logger.info("API CLEMENT BAYARD START");

    }
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxHttpHeaderSize(1048576);
            }
        });
        return factory;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (allowedUrl != null) {
                    logger.info("Allowed entering urls : " + allowedUrl);
                    registry.addMapping("/**").allowedOrigins(allowedUrl).allowedMethods("GET", "POST");
                }
            }
        };
    }


}
