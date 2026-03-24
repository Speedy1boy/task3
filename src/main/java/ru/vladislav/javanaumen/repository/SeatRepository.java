package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.JavaNaumen.entity.Seat;

@RepositoryRestResource(path = "seats")
public interface SeatRepository extends CrudRepository<Seat, Long> {
}
