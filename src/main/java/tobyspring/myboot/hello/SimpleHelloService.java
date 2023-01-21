package tobyspring.myboot.hello;

import org.springframework.stereotype.Service;

//@Service
@MyComponent
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }
}
