package ru.vladislav.JavaNaumen.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vladislav.JavaNaumen.entity.Ticket;

import java.util.List;

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
