package com.example.aspex1.service;

import com.example.aspex1.entity.Booking;
import com.example.aspex1.entity.DiningTable;
import com.example.aspex1.entity.User;
import com.example.aspex1.payload.BookingDto;
import com.example.aspex1.repository.BookingRepository;
import com.example.aspex1.repository.DiningTableRepository;
import com.example.aspex1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DiningTableService diningTableService;
    private final DiningTableRepository diningTableRepository;
    public Booking createBooking(BookingDto bookingDto){
        Long userId = bookingDto.getUserID();
        int capacity = bookingDto.getCapacity();
        LocalTime bookingTime = bookingDto.getBookingTime();

        User user = userRepository.getUserById(userId);
        List<DiningTable> availableTables = diningTableService.getAvailableTablesByCapacityAndTime(capacity, bookingTime);
        if (availableTables.isEmpty()) {
            throw new IllegalArgumentException("No available tables for the specified capacity and time");
        }
        DiningTable table = availableTables.get(0);
        Booking booking = new Booking();
        booking.setCapacity(capacity);
        booking.setUser(user);
        booking.setTable(table);
        booking.setBookingTime(LocalTime.from(bookingTime));
        table.setBookingTime(bookingTime);
        System.out.println(table.getBookingTime());
        booking.setEndTime(bookingTime.plusHours(2));
        diningTableRepository.updateBookingTime(table.getTable_id(), bookingTime);

        diningTableRepository.save(table);
        bookingRepository.save(booking);

        return booking;


    }

}
