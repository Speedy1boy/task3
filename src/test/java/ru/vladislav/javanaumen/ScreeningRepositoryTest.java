package ru.vladislav.javanaumen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vladislav.javanaumen.entity.Screening;
import ru.vladislav.javanaumen.repository.ScreeningRepository;

@SpringBootTest
class ScreeningRepositoryTest {
    private final ScreeningRepository screeningRepository;

    @Autowired
    ScreeningRepositoryTest(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Test
    void testFindByPrice() {
        var screening = new Screening();
        screening.setFormat("IMAX");
        screening.setPrice(12.0);

        screeningRepository.save(screening);

        var screenings = screeningRepository.findByPrice(10.0, 15.0, "IMAX");

        Assertions.assertFalse(screenings.isEmpty());
        Assertions.assertEquals("IMAX", screenings.getFirst().getFormat());
    }
}
