package com.scorpio.application_service;

import com.scorpio.data_transfer.WeatherDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "WEATHER-SERVICE",fallback = WeatherReportServiceFallbackImpl.class)
public interface WeatherReportService {

    @GetMapping("/weather-service/findAll")
    List<WeatherDTO> findAll();

    @GetMapping("/weather-service/findOne")
    WeatherDTO findOne();
}
