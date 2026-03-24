package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.JavaNaumen.entity.Movie;

@RepositoryRestResource(path = "movies")
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
