package kr.co.test.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.test.eatgo.application.EmailNotExistedException;
import kr.co.test.eatgo.application.PasswordWrongException;

@ControllerAdvice
public class SessionErrorAdvice {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PasswordWrongException.class)
	public String handlePasswordWrong() {
		return "{}";
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailNotExistedException.class)
	public String handleEmailNotExisted() {
		return "{}";
	}
}
