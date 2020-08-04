package com.tts.weatherapp.service;

import com.tts.weatherapp.model.Response;
import com.tts.weatherapp.model.ZipCode;
import com.tts.weatherapp.repository.ZipCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api_key}")
	private String apiKey;
	
    @Autowired
	private ZipCodeRepository ZipCodeRepository;

    public Response getForecast(String zipCode) {
		String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode
				+ "&units=imperial&appid=" + apiKey; 
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ZipCode zip = new ZipCode();
			ZipCodeRepository.save(zip);
			return restTemplate.getForObject(url, Response.class); 
		} catch (HttpClientErrorException ex) {
			Response response = new Response();
			response.setName("error"); 
			return response; 
		}

	}

}