package semicolon.africa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
public class MyOptioningApp {
    public static void main(String[] args) {
        SpringApplication.run(MyOptioningApp.class, args);
    }
}