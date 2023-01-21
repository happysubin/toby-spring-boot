package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.myboot.hello.HelloController;
import tobyspring.myboot.hello.SimpleHelloService;

public class MybootApplication {

	public static void main(String[] args) {

		/**
		 * 서블릿 컨테이너 작업 및 초기화를 스프링 컨테이너 초기화 과정중에 발생하도록 수정. 스프링 부트가 이런 방식.
		 */
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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

		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class); //인터페이스가 아닌 구체 클래스를 넣어줘야함.
		applicationContext.refresh(); //구성 정보를 가지고 컨테이너를 초기화함. 빈 오브젝트를 다 만듦. 템플릿 메서드 방식임.
	}
}
