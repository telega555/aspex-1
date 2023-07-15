package com.example.aspex1.repository;

import com.example.aspex1.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {


    @Modifying
    @Query("UPDATE DiningTable d SET d.bookingTime = :bookingTime WHERE d.table_id = :diningId")
    void updateBookingTime(@Param("diningId") Long diningId, @Param("bookingTime") LocalTime bookingTime);
    List<DiningTable> findByCapacity(int capacity);
}