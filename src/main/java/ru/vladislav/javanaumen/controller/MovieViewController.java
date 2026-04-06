package ru.vladislav.javanaumen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vladislav.javanaumen.repository.MovieRepository;

@Controller
@RequestMapping("/custom/movies/view")
public class MovieViewController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/list")
    public String movieListView(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movieList";
    }
}
