package tobyspring.myboot.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;

import tobyspring.myboot.config.ConditionalMyOnClass;
import tobyspring.myboot.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    @Bean("tomcatWebserverFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
