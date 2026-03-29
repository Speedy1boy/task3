package ru.vladislav.javanaumen;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vladislav.javanaumen.entity.Seat;
import ru.vladislav.javanaumen.entity.Screening;
import ru.vladislav.javanaumen.repository.BookingRepository;
import ru.vladislav.javanaumen.repository.ScreeningRepository;
import ru.vladislav.javanaumen.repository.SeatRepository;
import ru.vladislav.javanaumen.service.BookingService;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Test
    void testCreateBooking() {
        var screening = new Screening();
        screening.setPrice(10.0);
        screeningRepository.save(screening);

        var seat = new Seat();
        seat.setSeatNumber(1);
        seatRepository.save(seat);

        var booking = bookingService.createBooking(
                UUID.randomUUID().toString(),
                "test@mail.com",
                screening,
                List.of(seat)
        );

        var found = bookingRepository.findById(booking.getId());

        Assertions.assertTrue(found.isPresent());
    }

    @Test
    void testCreateBookingRollback() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookingService.createBooking(
                    "Test",
                    "test@mail.com",
                    null,
                    List.of()
            );
        });
    }
}
