package com.beiming.exception;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beiming.common.result.APIResult;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler {

	/**
	 * 参数校验
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public APIResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult br = e.getBindingResult();
		String message = br.getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		log.error("validation error {}", message, e);
		return APIResult.fail(message);
	}
	
	  @ExceptionHandler(Exception.class)
	  public APIResult processException(Exception e) {
	    log.error("exception", e);
	    return APIResult.fail(e.getMessage());
	  }
}
