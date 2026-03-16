package ru.vladislav.JavaNaumen.dao;

import java.util.List;
import ru.vladislav.JavaNaumen.entity.Ticket;

public interface TicketRepositoryCustom {
    // Найти билеты по названию фильма
    List<Ticket> findByMovieTitle(String title);
}
