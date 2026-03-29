package ru.vladislav.javanaumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vladislav.javanaumen.service.MovieService;

@Component
public class CommandProcessor {
    private final MovieService movieService;

    @Autowired
    public CommandProcessor(MovieService movieService) {
        this.movieService = movieService;
    }

    public void processCommand(String input) {
        if (input.isBlank()) return;
        var cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                movieService.createMovie(Long.valueOf(cmd[1]), cmd[2], cmd[3]);
                System.out.println("Фильм успешно создан.");
            }
            case "find" -> {
                var movie = movieService.findById(Long.valueOf(cmd[1]));
                System.out.println(movie);
            }
            case "delete" -> {
                movieService.deleteById(Long.valueOf(cmd[1]));
                System.out.println("Фильм удалён.");
            }
            case "updname" -> {
                movieService.updateName(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Название фильма обновлено.");
            }
            case "updgenre" -> {
                movieService.updateGenre(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Жанр фильма обновлён.");
            }
            default -> System.out.println("Введена неизвестная команда.");
        }
    }
}
