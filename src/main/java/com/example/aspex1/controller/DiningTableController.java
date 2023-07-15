package com.example.aspex1.controller;

import com.example.aspex1.entity.DiningTable;
import com.example.aspex1.payload.DiningTableDto;
import com.example.aspex1.service.DiningTableService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
@Secured({"ADMIN"})
@RestController
@RequestMapping("/tables")
public class DiningTableController {

    private final DiningTableService tableService;

    @Autowired
    public DiningTableController(DiningTableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/available/{capacity}")
    public ResponseEntity<String> getAvailableTablesByCapacity(@PathVariable int capacity, LocalTime localTime) {
        tableService.getAvailableTablesByCapacityAndTime(capacity,localTime);
        return new ResponseEntity<>("Yes, This table is free", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<DiningTableDto> createTable(@RequestBody DiningTableDto tableDTO) {
        System.out.println(tableDTO.getCapacity());
       return new ResponseEntity<>(tableService.createDiningTable(tableDTO), HttpStatus.OK);
    }
}
