package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.Seat;

@RepositoryRestResource(path = "seats")
public interface SeatRepository extends CrudRepository<Seat, Long> {
}
