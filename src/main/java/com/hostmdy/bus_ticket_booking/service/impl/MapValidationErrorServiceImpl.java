package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorServiceImpl implements com.hostmdy.bus_ticket_booking.service.MapValidationErrorService{

	@Override
	public ResponseEntity<Map<String, String>> validate(BindingResult result) {
		// TODO Auto-generated method stub
		if(result.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			for(final FieldError error : fieldErrors) {
				errorMap.put(error.getField(),error.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
		}
		
		return null;
	}

}
