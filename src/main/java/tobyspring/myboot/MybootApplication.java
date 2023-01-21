package tobyspring.myboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tobyspring.myboot.hello.HelloController;
import tobyspring.myboot.hello.SimpleHelloService;

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


		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class); //인터페이스가 아닌 구체 클래스를 넣어줘야함.
		applicationContext.refresh(); //구성 정보를 가지고 컨테이너를 초기화함. 빈 오브젝트를 다 만듦.
		/**
		 * 디스패처 서블릿을 등록
		 * 웹 앱 로직은 다른 객체에게 책임을 위임해야함.
		 */

		WebServer webServer = servletFactory.getWebServer(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.addServlet("frontcontroller", new HttpServlet() {
					@Override
					public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

						if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){

							HelloController controller = applicationContext.getBean(HelloController.class);
							String name = controller.hello(req.getParameter("name"));

							//resp.setStatus(HttpStatus.OK.value()); 서블릿이 알아서 200번이나 오류가 발생하면 적절한 상태 코드를 리턴함.

							resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
							resp.getWriter().println(name);
						}

						else if (req.getRequestURI().equals("/user")){
							//
						}
						else{
							resp.setStatus(HttpStatus.NOT_FOUND.value());
						}
					}
				}).addMapping("/*");
			}
		});
		webServer.start();
	}
}
