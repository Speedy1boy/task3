package ru.vladislav.javanaumen.dao;

import java.util.List;
import ru.vladislav.javanaumen.entity.Screening;

public interface ScreeningRepositoryCustom {
    // Поиск сеансов по диапазону цены и формату
    List<Screening> findByPrice(Double minPrice, Double maxPrice, String format);
}
