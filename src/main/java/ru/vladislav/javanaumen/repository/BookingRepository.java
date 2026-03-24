package ru.vladislav.JavaNaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.JavaNaumen.entity.Booking;

@RepositoryRestResource(path = "bookings")
public interface BookingRepository extends CrudRepository<Booking, Long> {
}
