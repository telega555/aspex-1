package com.example.aspex1.controller;

import com.example.aspex1.entity.Booking;
import com.example.aspex1.payload.BookingDto;
import com.example.aspex1.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingDto), HttpStatus.OK);
    }

}
