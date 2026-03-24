package ru.vladislav.JavaNaumen.service;

import ru.vladislav.JavaNaumen.entity.Movie;

public interface MovieService {
    void createMovie(Long id, String name, String genre);

    Movie findById(Long id);

    void deleteById(Long id);

    void updateName(Long id, String newName);

    void updateGenre(Long id, String newGenre);
}
