package pers.ztcly.andesiteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pers.ztcly.andesiteapi","pers.ztcly.andesitedb"})
public class AndesiteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndesiteApiApplication.class, args);
    }

}
