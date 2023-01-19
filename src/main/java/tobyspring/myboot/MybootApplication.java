package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class MybootApplication {

	public static void main(String[] args) {

		/**
		 * 톰캣 웹 서버, 즉 웹 컨테이너인 서블릿 컨테이를 실행하는 코드.
		 * TomcatServletWebServerFactory를 사용해 복잡한 설정을 생략 가능.
		 */
		TomcatServletWebServerFactory servletFactory = new TomcatServletWebServerFactory();
		WebServer webServer = servletFactory.getWebServer();
		webServer.start();
	}
}
