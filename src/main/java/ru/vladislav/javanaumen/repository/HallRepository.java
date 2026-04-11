package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.Hall;

@RepositoryRestResource(path = "halls")
public interface HallRepository extends CrudRepository<Hall, Long> {
}
