package tobyspring.myboot;

import org.springframework.boot.SpringApplication;

import tobyspring.myboot.hello.MySpringBootAnnotation;

@MySpringBootAnnotation
public class MybootApplicationV2 {

	public static void main(String[] args) {
		SpringApplication.run(MybootApplicationV2.class, args);
	}
}
