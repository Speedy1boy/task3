package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vladislav.JavaNaumen.entity.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
