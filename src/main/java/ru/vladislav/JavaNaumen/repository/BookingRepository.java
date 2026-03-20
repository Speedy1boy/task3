package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vladislav.JavaNaumen.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
