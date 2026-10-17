package com.example.enterprise.exceptions;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Automatically mapping to HTTP 409
public class ConflictException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
        super(message);
    }
}
