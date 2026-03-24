package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.JavaNaumen.entity.Hall;

@RepositoryRestResource(path = "halls")
public interface HallRepository extends CrudRepository<Hall, Long> {
}
