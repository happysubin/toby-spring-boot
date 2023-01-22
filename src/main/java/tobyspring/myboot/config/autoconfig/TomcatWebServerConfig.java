package tobyspring.myboot.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.myboot.config.MyAutoConfiguration;

@MyAutoConfiguration
public class TomcatWebServerConfig {

    @Bean("tomcatWebserverFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
