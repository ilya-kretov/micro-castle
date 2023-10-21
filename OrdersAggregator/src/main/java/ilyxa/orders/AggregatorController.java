package ilyxa.orders;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    private final Faker faker = new Faker();
    @GetMapping("/hello")
    public String sayHello() {
        String result = "Hello " + faker.name().fullName();
        System.out.println(result);
        return result;
    }
}
