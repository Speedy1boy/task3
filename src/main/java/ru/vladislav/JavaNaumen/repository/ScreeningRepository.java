package ru.vladislav.JavaNaumen.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.vladislav.JavaNaumen.dao.ScreeningRepositoryCustom;
import ru.vladislav.JavaNaumen.entity.Screening;

public interface ScreeningRepository extends CrudRepository<Screening, Long>, ScreeningRepositoryCustom {
    // Поиск сеансов по диапазону цены и формату
    List<Screening> findByPrice(Double minPrice, Double maxPrice, String format);
}
