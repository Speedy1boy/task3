package ru.vladislav.javanaumen.service;

import java.util.List;

import ru.vladislav.javanaumen.entity.Booking;
import ru.vladislav.javanaumen.entity.Seat;
import ru.vladislav.javanaumen.entity.Screening;

public interface BookingService {
    // Создание брони с билетами
    Booking createBooking(String customerName, String customerEmail, Screening screening, List<Seat> seats);
}
