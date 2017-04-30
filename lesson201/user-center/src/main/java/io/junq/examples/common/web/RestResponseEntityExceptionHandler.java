package io.junq.examples.common.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.junq.examples.common.web.exception.ApiError;
import io.junq.examples.common.web.exception.IJBadRequestException;
import io.junq.examples.common.web.exception.IJConflictException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // API

    // 400
   
    @Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers,
				HttpStatus.BAD_REQUEST, request);
	}
   
    @Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
	}
    
    @ExceptionHandler(value = {DataIntegrityViolationException.class, IJBadRequestException.class, IJConflictException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
    	return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    // 500
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleServerError(final RuntimeException ex, final WebRequest request) {
    	return handleExceptionInternal(ex, message(HttpStatus.INTERNAL_SERVER_ERROR, ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    private final ApiError message(final HttpStatus httpStatus, final Exception ex) {
    	final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
    	final String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
    	
    	return new ApiError(httpStatus.value(), message, developerMessage);
    }
}
