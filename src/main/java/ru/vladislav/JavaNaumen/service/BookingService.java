package ru.vladislav.JavaNaumen.service;

import java.util.List;

import ru.vladislav.JavaNaumen.entity.Booking;
import ru.vladislav.JavaNaumen.entity.Seat;
import ru.vladislav.JavaNaumen.entity.Screening;

public interface BookingService {
    // Создание брони с билетами
    Booking createBooking(String customerName, String customerEmail, Screening screening, List<Seat> seats);
}
