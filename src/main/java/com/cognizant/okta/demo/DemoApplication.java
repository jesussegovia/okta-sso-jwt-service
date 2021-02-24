package com.cognizant.okta.demo;

import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cognizant.okta.demo.bean.Car;
import com.cognizant.okta.demo.repository.CarRepository;

@SpringBootApplication
public class DemoApplication {

	@Value("${app.client}")
	private String urlClient;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(CarRepository repository) {
		return args ->{
			
			//initial Data
			String[] data= {"Ferrari","Jaguar","Porsche","Lamborgini","Bugatti","AMC","Triumph","Ford","YugoGV"};
			Stream.of(data).forEach(name->{
				Car car=new Car();
				car.setName(name);
				repository.save(car);
			});
			repository.findAll().forEach(System.out::println);

		};
	}
	
	 @Bean
	    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
	

			System.out.println("urlClient "+urlClient);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.setAllowedOrigins(Collections.singletonList(urlClient));
	        config.setAllowedMethods(Collections.singletonList("*"));
	        config.setAllowedHeaders(Collections.singletonList("*"));
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return bean;
	    }

}
