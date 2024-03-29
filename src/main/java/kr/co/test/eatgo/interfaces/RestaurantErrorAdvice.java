package kr.co.test.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.test.eatgo.domain.RestaurantNotFoundException;

@ControllerAdvice
public class RestaurantErrorAdvice {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RestaurantNotFoundException.class)
	public String handleNotFound() {
		
		return"{}";	
	}
}
