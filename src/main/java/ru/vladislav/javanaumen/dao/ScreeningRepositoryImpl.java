package ru.vladislav.javanaumen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import ru.vladislav.javanaumen.entity.Screening;

@Repository
public class ScreeningRepositoryImpl implements ScreeningRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public ScreeningRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Screening> findByPrice(Double minPrice, Double maxPrice, String format) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Screening.class);

        var screeningRoot = criteriaQuery.from(Screening.class);

        var priceBetween = criteriaBuilder.between(screeningRoot.get("price"), minPrice, maxPrice);
        var formatEqual = criteriaBuilder.equal(screeningRoot.get("format"), format);

        criteriaQuery.select(screeningRoot).where(criteriaBuilder.and(priceBetween, formatEqual));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
