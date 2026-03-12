package ru.vladislav.JavaNaumen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vladislav.JavaNaumen.entity.Movie;
import ru.vladislav.JavaNaumen.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovie(Long id, String name, String genre) {
        var newMovie = new Movie();
        newMovie.setId(id);
        newMovie.setName(name);
        newMovie.setGenre(genre);
        movieRepository.create(newMovie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public void updateName(Long id, String newName) {
        var movie = movieRepository.read(id);
        if (movie != null) {
            movie.setName(newName);
            movieRepository.update(movie);
        }
    }

    @Override
    public void updateGenre(Long id, String newGenre) {
        var movie = movieRepository.read(id);
        if (movie != null) {
            movie.setGenre(newGenre);
            movieRepository.update(movie);
        }
    }
}
