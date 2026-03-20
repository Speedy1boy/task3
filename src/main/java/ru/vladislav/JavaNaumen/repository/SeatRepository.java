package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vladislav.JavaNaumen.entity.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long> {
}
