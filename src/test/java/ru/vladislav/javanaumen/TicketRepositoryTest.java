package ru.vladislav.javanaumen;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vladislav.javanaumen.entity.Movie;
import ru.vladislav.javanaumen.entity.Screening;
import ru.vladislav.javanaumen.entity.Ticket;
import ru.vladislav.javanaumen.repository.MovieRepository;
import ru.vladislav.javanaumen.repository.ScreeningRepository;
import ru.vladislav.javanaumen.repository.TicketRepository;

@SpringBootTest
class TicketRepositoryTest {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    void testFindByMovieTitle() {
        var movieTitle = UUID.randomUUID().toString();

        var movie = new Movie();
        movie.setTitle(movieTitle);
        movieRepository.save(movie);

        var screening = new Screening();
        screening.setMovie(movie);
        screening.setStartTime(LocalDateTime.now());
        screeningRepository.save(screening);

        var ticket = new Ticket();
        ticket.setScreening(screening);
        ticketRepository.save(ticket);

        var tickets = ticketRepository.findByMovieTitle(movieTitle);

        Assertions.assertFalse(tickets.isEmpty());
    }
}
