package com.example.aspex1.payload;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class BookingDto {
    private LocalTime bookingTime;
    private int capacity;
    private Long userID;


}
