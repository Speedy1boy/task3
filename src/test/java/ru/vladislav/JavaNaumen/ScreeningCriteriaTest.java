package ru.vladislav.JavaNaumen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vladislav.JavaNaumen.entity.Screening;
import ru.vladislav.JavaNaumen.repository.ScreeningRepository;

@SpringBootTest
class ScreeningCriteriaTest {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    void testFindByPriceCriteria() {
        var screening = new Screening();
        screening.setFormat("3D");
        screening.setPrice(9.0);

        screeningRepository.save(screening);

        var result = screeningRepository.findByPrice(5.0, 10.0, "3D");

        Assertions.assertFalse(result.isEmpty());
    }
}
