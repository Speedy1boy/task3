package ru.vladislav.JavaNaumen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vladislav.JavaNaumen.entity.Screening;
import ru.vladislav.JavaNaumen.entity.Ticket;
import ru.vladislav.JavaNaumen.repository.ScreeningRepository;
import ru.vladislav.JavaNaumen.repository.TicketRepository;

@RestController
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/screenings/findByPrice")
    public List<Screening> findScreenings(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam String format) {
        return screeningRepository.findByPrice(minPrice, maxPrice, format);
    }

    @GetMapping("/tickets/findByMovieTitle")
    public List<Ticket> findTickets(@RequestParam String title) {
        return ticketRepository.findByMovieTitle(title);
    }
}
