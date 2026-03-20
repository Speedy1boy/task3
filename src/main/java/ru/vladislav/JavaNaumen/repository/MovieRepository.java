package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vladislav.JavaNaumen.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
