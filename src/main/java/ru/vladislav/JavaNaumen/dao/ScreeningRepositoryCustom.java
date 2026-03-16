package ru.vladislav.JavaNaumen.dao;

import java.util.List;
import ru.vladislav.JavaNaumen.entity.Screening;

public interface ScreeningRepositoryCustom {
    // Поиск сеансов по диапазону цены и формату
    List<Screening> findByPrice(Double minPrice, Double maxPrice, String format);
}
