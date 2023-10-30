package com.springproject.expenseapi.exception;

import java.net.http.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springproject.expenseapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ErrorObject> hadleExpenseNotFoundException(ExpenseNotFoundException ex,WebRequest request){
		
		ErrorObject eObject=new ErrorObject();
		
		eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		eObject.setMessage(ex.getMessage());
		
		eObject.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorObject>(eObject,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentMissmatchException(MethodArgumentTypeMismatchException ex,WebRequest request){
		
		ErrorObject eObject=new ErrorObject();
		
		eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		eObject.setMessage(ex.getMessage());
		
		eObject.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorObject>(eObject,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex,WebRequest request){
		
		ErrorObject eObject=new ErrorObject();
		
		eObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		eObject.setMessage(ex.getMessage());
		
		eObject.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorObject>(eObject,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders header,HttpStatus status,WebRequest request){
		
		Map<String, Object> bodyMap=new LinkedHashMap<>();
		bodyMap.put("timestamp", System.currentTimeMillis());
		bodyMap.put("statuscode", status.value());
		
		List<String> errorList=ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x->x.getDefaultMessage())
				.collect(Collectors.toList());
		
		bodyMap.put("errors", errorList);
		
		return new ResponseEntity<Object>(bodyMap,status);
	}
	
	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<Object> handleItemExistsException(ItemAlreadyExistsException ex,WebRequest request){
		ErrorObject eObject=new ErrorObject();
		eObject.setStatusCode(HttpStatus.CONFLICT.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<Object>(eObject,HttpStatus.CONFLICT);
	}
}
