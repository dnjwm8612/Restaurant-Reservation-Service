package kr.co.test.eatgo.application;

public class EmailUserExistedException extends RuntimeException{

	public EmailUserExistedException(String email) {
		super("Email us already registered: " + email);
	}
	
}
