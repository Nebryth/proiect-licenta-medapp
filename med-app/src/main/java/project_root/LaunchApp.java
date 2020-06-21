package project_root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan()
@SpringBootApplication
public class LaunchApp {
    public static void main(String[] args) {
        SpringApplication.run(LaunchApp.class, args);
        System.out.println("Inside MAIN");
    }
}
