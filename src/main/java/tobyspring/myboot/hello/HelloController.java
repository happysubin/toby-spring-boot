package tobyspring.myboot.hello;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService simpleHelloService) {
        this.helloService = simpleHelloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
