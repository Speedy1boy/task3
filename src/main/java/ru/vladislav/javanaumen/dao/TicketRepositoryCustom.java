package ru.vladislav.javanaumen.dao;

import java.util.List;
import ru.vladislav.javanaumen.entity.Ticket;

public interface TicketRepositoryCustom {
    // Найти билеты по названию фильма
    List<Ticket> findByMovieTitle(String title);
}
