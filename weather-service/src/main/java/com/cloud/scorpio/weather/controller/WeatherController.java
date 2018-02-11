package com.cloud.scorpio.weather.controller;

import com.cloud.scorpio.weather.application_service.WeatherReportServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="Weather",description = "Weather service end point")
public class WeatherController {

    @Autowired
    private WeatherReportServiceImpl weatherReportService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(weatherReportService.findAll());

    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOne(){
        return ResponseEntity.ok(weatherReportService.findOne());

    }

    @GetMapping("/sanity/check")
    @ApiOperation(value = "Sanity Check is ok", notes = "Check system is ok",nickname = "getSanityCheck")
    public ResponseEntity<?> sanity(){
        return ResponseEntity.ok("Status Ok");
    }
}
