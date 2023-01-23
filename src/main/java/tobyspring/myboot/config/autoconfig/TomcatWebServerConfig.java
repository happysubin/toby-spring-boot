package tobyspring.myboot.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;

import org.springframework.core.env.Environment;
import tobyspring.myboot.config.ConditionalMyOnClass;
import tobyspring.myboot.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    @Value("${contextPath}")
    String contextPath;

    @Bean("tomcatWebserverFactory")
    @ConditionalOnMissingBean //똑같은 타입의 빈이 없어야지 빈으로 등록된다.
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(this.contextPath);
        return serverFactory;
    }
}
