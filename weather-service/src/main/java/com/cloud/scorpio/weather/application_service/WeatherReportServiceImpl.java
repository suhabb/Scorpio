package com.cloud.scorpio.weather.application_service;

import com.cloud.scorpio.weather.data_transfer.WeatherDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeatherReportServiceImpl {


    public List<WeatherDTO> findAll() {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCity("Bangalore");
        weatherDTO.setCountry("India");
        weatherDTO.setTemperature(37);

        WeatherDTO weatherDTO2 = new WeatherDTO();
        weatherDTO2.setCity("Hyderabad");
        weatherDTO2.setCountry("India");
        weatherDTO2.setTemperature(38);

        return Arrays.asList(weatherDTO, weatherDTO2);
    }


    public WeatherDTO findOne() {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCity("Bangalore");
        weatherDTO.setCountry("India");
        weatherDTO.setTemperature(37);
        return weatherDTO;

    }
}
