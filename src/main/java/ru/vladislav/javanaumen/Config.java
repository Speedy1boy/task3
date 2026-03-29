package ru.vladislav.javanaumen;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ru.vladislav.javanaumen.entity.Movie;

@Configuration
public class Config {
    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @Bean
    public CommandLineRunner commandLineRunner(CommandProcessor commandProcessor) {
        return args -> {
            try (var scanner = new Scanner(System.in)) {
                System.out.println("Введите команду \"exit\" для выхода.");
                while (true) {
                    System.out.print("> ");
                    var input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Movie> movieContainer() {
        return new ArrayList<>();
    }

    @PostConstruct
    public void printInfo() {
        System.out.printf("%s v%s.%n", name, version);
    }
}
