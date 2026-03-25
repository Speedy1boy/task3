package ru.vladislav.JavaNaumen.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vladislav.JavaNaumen.entity.Movie;

@Component
public class MovieRepository implements CrudRepository<Movie, Long> {
    private final List<Movie> movieContainer;

    @Autowired
    public MovieRepository(List<Movie> movieContainer) {
        this.movieContainer = movieContainer;
    }

    @Override
    public void create(Movie entity) {
        movieContainer.add(entity);
    }

    @Override
    public Movie read(Long id) {
        for (var movie : movieContainer) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void update(Movie entity) {
        for (var i = 0; i < movieContainer.size(); i++) {
            var movie = movieContainer.get(i);
            if (movie.getId().equals(entity.getId())) {
                movieContainer.set(i, entity);
                break;
            }
        }
    }

    @Override
    public void delete(Long id) {
        for (var i = 0; i < movieContainer.size(); i++) {
            var movie = movieContainer.get(i);
            if (movie.getId().equals(id)) {
                movieContainer.remove(i);
                break;
            }
        }
    }
}
