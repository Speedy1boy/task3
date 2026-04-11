package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.Movie;

@RepositoryRestResource(path = "movies")
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
