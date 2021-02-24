

package com.cognizant.okta.demo.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognizant.okta.demo.bean.Car;

import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CarRepository extends JpaRepository<Car, Long> {
	

}

	
