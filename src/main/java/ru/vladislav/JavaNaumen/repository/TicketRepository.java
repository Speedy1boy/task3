package ru.vladislav.JavaNaumen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.vladislav.JavaNaumen.dao.TicketRepositoryCustom;
import ru.vladislav.JavaNaumen.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long>, TicketRepositoryCustom {
    // Найти билеты по названию фильма
    @Query("FROM Ticket t WHERE t.screening.movie.title = :title")
    List<Ticket> findByMovieTitle(String title);
}
