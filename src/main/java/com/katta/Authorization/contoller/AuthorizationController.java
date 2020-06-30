package com.katta.Authorization.contoller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/v1", produces = "application/json")
@RestController
public class AuthorizationController {

	@RequestMapping(method = RequestMethod.GET, value = "/validateAuth")
	public ResponseEntity<String> validateAuthToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		return Optional.ofNullable(token).
				filter(value -> value.equalsIgnoreCase("Valid"))
				.map(value -> returnSuccess())
				.orElse(returnFailure(token));
	}

	private ResponseEntity<String> returnSuccess() {
		return new ResponseEntity<>("Valid", HttpStatus.OK);
	}

	private ResponseEntity<String> returnFailure(String token) {
		if(StringUtils.isBlank(token))
			return new ResponseEntity<>("Token should not be empty/null", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>("InValid", HttpStatus.BAD_REQUEST);
	}

}
