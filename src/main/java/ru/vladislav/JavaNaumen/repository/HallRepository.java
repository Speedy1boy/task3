package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vladislav.JavaNaumen.entity.Hall;

public interface HallRepository extends CrudRepository<Hall, Long> {
}
