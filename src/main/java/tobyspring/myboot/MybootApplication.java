package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.myboot.hello.HelloController;
import tobyspring.myboot.hello.HelloService;
import tobyspring.myboot.hello.SimpleHelloService;

@Configuration
public class MybootApplication {

	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService); }
	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}

	public static void main(String[] args) {

		/**
		 * 서블릿 컨테이너 작업 및 초기화를 스프링 컨테이너 초기화 과정중에 발생하도록 수정. 스프링 부트가 이런 방식.
		 * AnnotationConfigWebApplicationContext 애노테이션 자바 구성을 인식할 수 있음.
		 */

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
							.addMapping("/*");
			});
			webServer.start();
			}
		};

		applicationContext.register(MybootApplication.class);
		applicationContext.refresh(); //구성 정보를 가지고 컨테이너를 초기화함. 빈 오브젝트를 다 만듦. 템플릿 메서드 방식임.
	}
}
