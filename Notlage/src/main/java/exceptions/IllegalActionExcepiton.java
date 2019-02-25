package exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
* Basis exception für fehlgeschlagene REST calls
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IllegalActionExcepiton extends RuntimeException{
	
	public IllegalActionExcepiton(String exception) 
	{
	    super(exception);
	}

}
