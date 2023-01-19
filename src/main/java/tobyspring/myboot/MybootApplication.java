package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MybootApplication {

	public static void main(String[] args) {

		/**
		 * 톰캣 웹 서버, 즉 웹 컨테이너인 서블릿 컨테이를 실행하는 코드.
		 * TomcatServletWebServerFactory를 사용해 복잡한 설정을 생략 가능.
		 */
		TomcatServletWebServerFactory servletFactory = new TomcatServletWebServerFactory();

		/**
		 * 서블릿을 등록
		 */
		WebServer webServer = servletFactory.getWebServer(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.addServlet("hello", new HttpServlet() {
					@Override
					public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						/**
						 * 응답 값을 설정.
						 */
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println("Hello Servlet");
					}
				}).addMapping("/hello");
			}
		});
		webServer.start();
	}
}
