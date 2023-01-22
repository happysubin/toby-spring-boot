package tobyspring.myboot.hello;

import org.springframework.boot.SpringApplication;

import tobyspring.myboot.config.MySpringBootApplication;

//@SpringBootApplication
@MySpringBootApplication
public class MybootApplicationV2 {

	public static void main(String[] args) {
		SpringApplication.run(MybootApplicationV2.class, args);
	}
}
