package com.scorpio.application_service;

import com.scorpio.data_transfer.WeatherDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WeatherReportServiceFallbackImpl implements WeatherReportService {

    @Override
    public List<WeatherDTO> findAll() {
        return Collections.emptyList();
    }

    @Override
    public WeatherDTO findOne() {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCity("NA");
        weatherDTO.setCountry("NA");
        weatherDTO.setTemperature(0);
        return weatherDTO;
    }
}
