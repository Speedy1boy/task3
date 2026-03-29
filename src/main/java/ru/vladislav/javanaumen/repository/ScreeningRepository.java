package ru.vladislav.javanaumen.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.dao.ScreeningRepositoryCustom;
import ru.vladislav.javanaumen.entity.Screening;

@RepositoryRestResource(path = "screenings")
public interface ScreeningRepository extends CrudRepository<Screening, Long>, ScreeningRepositoryCustom {
    // Поиск сеансов по диапазону цены и формату
    List<Screening> findByPrice(Double minPrice, Double maxPrice, String format);
}
