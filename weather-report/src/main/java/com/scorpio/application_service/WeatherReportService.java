package com.scorpio.application_service;

import com.scorpio.data_transfer.WeatherDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "WEATHER-SERVICE",fallback = WeatherReportServiceFallbackImpl.class)
//@FeignClient(name = "WEATHER-SERVICE",url = "http://localhost:8091/weather-service/")
public interface WeatherReportService {

    @GetMapping("/findAll")
    List<WeatherDTO> findAll();

    @GetMapping("/findOne")
    WeatherDTO findOne();
}
