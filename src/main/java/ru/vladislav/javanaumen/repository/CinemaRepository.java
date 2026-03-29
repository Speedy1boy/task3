package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.Cinema;

@RepositoryRestResource(path = "cinemas")
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
