package com.example.aspex1.repository;

import com.example.aspex1.entity.Booking;
import com.example.aspex1.entity.DiningTable;
import com.example.aspex1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByTable(DiningTable table);

    List<Booking> findByBookingTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

}