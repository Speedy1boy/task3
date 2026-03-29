package ru.vladislav.javanaumen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.dao.TicketRepositoryCustom;
import ru.vladislav.javanaumen.entity.Ticket;

@RepositoryRestResource(path = "tickets")
public interface TicketRepository extends CrudRepository<Ticket, Long>, TicketRepositoryCustom {
    // Найти билеты по названию фильма
    @Query("FROM Ticket t WHERE t.screening.movie.title = :title")
    List<Ticket> findByMovieTitle(String title);
}
