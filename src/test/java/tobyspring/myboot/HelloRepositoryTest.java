package tobyspring.myboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.myboot.hello.HelloRepository;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
public class HelloRepositoryTest {

    @Autowired
    HelloRepository helloRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @BeforeEach
    void beforeEach(){
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }


    @Test
    void findHelloFailed(){
        assertThat(helloRepository.findHello("subin")).isNull();
    }


    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("Toby")).isEqualTo(0);

        helloRepository.increaseCount("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(1);

        helloRepository.increaseCount("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
    }

}
