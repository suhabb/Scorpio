package com.scorpio.controller;

import com.scorpio.application_service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherReportController {

    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(weatherReportService.findAll());
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOne(){
        return ResponseEntity.ok(weatherReportService.findOne());
    }

    @GetMapping("/sanity/check")
    public ResponseEntity<?> check(){
        return ResponseEntity.ok("Status ok:Weather Report");
    }
}
