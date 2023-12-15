package com.javaspringproject.springboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

import static java.util.Arrays.asList;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student hello = new Student(
                        "Hello",
                        LocalDate.of(
                                2000,
                                Month.AUGUST,
                                3
                        ),
                        "Hello@gmail.com"
                );
            Student world = new Student(
                        "World",
                        LocalDate.of(
                                1992,
                                Month.AUGUST,
                                3
                        ),
                        "World@gmail.com"
                );
            repository.saveAll(
                    asList(hello, world)
            );
        };
    }
}
