package com.scorpio.service_access;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scorpio.data_transfer.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/*
* Only Hystrix without feign
*
* */
@Service
public class WeatherServiceAccess {

    //Autowired ribbon client load balancer
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(groupKey = "fallBack",
    commandKey = "fallBack",
    fallbackMethod = "findOneFallBack")
    public ResponseEntity<?> findOneHystrix(){
        return restTemplate.getForEntity("http://WEATHER-SERVICE/weather-service/findOne",WeatherDTO.class);

    }

    public ResponseEntity<?> findAll(){
        return restTemplate.getForEntity("http://WEATHER-SERVICE/weather-service/findAll",List.class);
        //return restTemplate.getForEntity("http://localhost:8091/weather-service/findAll",List.class);

    }



    public ResponseEntity<?> findOneFallBack(){
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCountry("Error");
        weatherDTO.setCity("Error");
        weatherDTO.setTemperature(0000);
        return ResponseEntity.ok(weatherDTO);

    }
}
