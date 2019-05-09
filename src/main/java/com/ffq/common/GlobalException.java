package com.ffq.common;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletResponse response, Exception ex){
		ex.printStackTrace();
		Integer code = 40000;
		if (ex instanceof Forbidden) {
			code = 40300;
		}
		return GlobalResponse.err(ex.getMessage(), code);
    }
}
