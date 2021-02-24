package com.cognizant.okta.demo.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.okta.demo.bean.Car;
import com.cognizant.okta.demo.repository.CarRepository;

@RestController
public class CoolCarController {
	
	private CarRepository repository;
	
	
	public CoolCarController(CarRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/cool-cars")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Car> coolCars(){
		return repository.findAll().stream().filter(this::isCool).collect(Collectors.toList());
	}


	private boolean isCool(Car car) {
		
		return !car.getName().equals("AMC")&&
				!car.getName().equals("Triumph")&&
				!car.getName().equals("Ford")&&
				!car.getName().equals("YugGV");
	}
	

}
