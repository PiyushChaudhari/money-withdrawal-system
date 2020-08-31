package org.globalvox.mws.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PageErrorHandler {

	
	@ExceptionHandler(value= {Exception.class})
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public String internalError(Exception ex) {
		return new String("error/page");
	}
}
