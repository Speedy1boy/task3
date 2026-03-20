package ru.vladislav.JavaNaumen.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ru.vladislav.JavaNaumen.entity.Booking;
import ru.vladislav.JavaNaumen.entity.Seat;
import ru.vladislav.JavaNaumen.entity.Screening;
import ru.vladislav.JavaNaumen.entity.Ticket;
import ru.vladislav.JavaNaumen.repository.BookingRepository;
import ru.vladislav.JavaNaumen.repository.TicketRepository;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              TicketRepository ticketRepository,
                              PlatformTransactionManager transactionManager) {
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public Booking createBooking(String customerName,
                                 String customerEmail,
                                 Screening screening,
                                 List<Seat> seats) {
        var status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            if (screening == null) {
                throw new IllegalArgumentException("Screening не может быть null");
            }
            if (seats == null || seats.isEmpty()) {
                throw new IllegalArgumentException("Список мест не может быть пустым");
            }

            var booking = new Booking();
            booking.setCustomerName(customerName);
            booking.setCustomerEmail(customerEmail);
            booking.setBookingTime(LocalDateTime.now());
            booking.setStatus("CREATED");

            bookingRepository.save(booking);

            for (var seat : seats) {
                var ticket = new Ticket();
                ticket.setBooking(booking);
                ticket.setScreening(screening);
                ticket.setSeat(seat);
                ticket.setPrice(screening.getPrice());
                ticket.setPurchaseTime(LocalDateTime.now());
                ticket.setTicketStatus("BOOKED");

                ticketRepository.save(ticket);
            }

            transactionManager.commit(status);

            return booking;
        } catch (DataAccessException | IllegalArgumentException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
