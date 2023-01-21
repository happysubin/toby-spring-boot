package tobyspring.myboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tobyspring.myboot.hello.SimpleHelloService;

public class HelloServiceTest {

    @Test
    void simpleHelloService(){

        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String result = simpleHelloService.sayHello("Test");

        Assertions.assertThat(result).isEqualTo("Hello Test");
    }
}
