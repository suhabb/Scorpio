package com.cloud.scorpio.weather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="Weather",description = "Weather service end point")
public class WeatherController {

    @GetMapping("/sanity/check")
    @ApiOperation(value = "Sanity Check is ok", notes = "Check system is ok",nickname = "getSanityCheck")
    public ResponseEntity<?> sanity(){
        return ResponseEntity.ok("Status Ok");
    }
}
