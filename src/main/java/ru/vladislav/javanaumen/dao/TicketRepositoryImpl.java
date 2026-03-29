package ru.vladislav.javanaumen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import ru.vladislav.javanaumen.entity.Ticket;

@Repository
public class TicketRepositoryImpl implements TicketRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public TicketRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ticket> findByMovieTitle(String title) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Ticket.class);

        var ticket = criteriaQuery.from(Ticket.class);

        var screeningJoin = ticket.join("screening");
        var movieJoin = screeningJoin.join("movie");

        var titlePredicate = criteriaBuilder.equal(movieJoin.get("title"), title);

        criteriaQuery.select(ticket).where(titlePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
