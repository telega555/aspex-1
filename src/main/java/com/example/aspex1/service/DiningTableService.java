package com.example.aspex1.service;

import com.example.aspex1.entity.Booking;
import com.example.aspex1.entity.DiningTable;
import com.example.aspex1.payload.DiningTableDto;
import com.example.aspex1.repository.DiningTableRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiningTableService {
    public final DiningTableRepository diningTableRepository;

    public DiningTableDto createDiningTable(DiningTableDto diningTableDto){
        System.out.println(diningTableDto.getCapacity());
        DiningTable diningTable = new DiningTable();
        diningTable.setCapacity(diningTableDto.getCapacity());

        DiningTable newDiningTable = diningTableRepository.save(diningTable);
        diningTableDto.setCapacity(newDiningTable.getCapacity());
        return diningTableDto;
    }
    public List<DiningTable> getAvailableTablesByCapacityAndTime(int capacity, LocalTime bookingTime) {
        List<DiningTable> availableTables = diningTableRepository.findByCapacity(capacity);

        return availableTables.stream()
                .filter(table -> isTableAvailable(table, bookingTime))
                .collect(Collectors.toList());
    }

    private boolean isTableAvailable(DiningTable table, LocalTime bookingTime) {
        List<Booking> bookings = table.getBookings();

        for (Booking booking : bookings) {
            LocalTime startTime = booking.getBookingTime();
            LocalTime endTime = startTime.plusHours(2);

            if (bookingTime.isAfter(startTime) && bookingTime.isBefore(endTime)) {
                return false; // Стол занят в указанное время
            }
        }

        return true; // Стол доступен для бронирования
    }
}
