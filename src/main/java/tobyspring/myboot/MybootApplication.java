package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.myboot.hello.HelloController;
import tobyspring.myboot.hello.SimpleHelloService;

public class MybootApplication {

	public static void main(String[] args) {

		/**
		 * 톰캣 웹 서버, 즉 웹 컨테이너인 서블릿 컨테이를 실행하는 코드.
		 * TomcatServletWebServerFactory를 사용해 복잡한 설정을 생략 가능.
		 */
		TomcatServletWebServerFactory servletFactory = new TomcatServletWebServerFactory();


		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class); //인터페이스가 아닌 구체 클래스를 넣어줘야함.
		applicationContext.refresh(); //구성 정보를 가지고 컨테이너를 초기화함. 빈 오브젝트를 다 만듦.

		/**
		 * 디스패처 서블릿을 등록
		 * 웹 앱 로직은 다른 객체에게 책임을 위임해야함.
		 */

		WebServer webServer = servletFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
					new DispatcherServlet(applicationContext)
			).addMapping("/*");
		});
		webServer.start();
	}
}
