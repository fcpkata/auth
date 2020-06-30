package com.katta.Authorization.contoller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.katta.Authorization.contoller.AuthorizationController;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationControllerTest {

	private static final String VALID = "Valid";
	private static final String INVALID = "InValid";
	private static final String EMPTY = "Token should not be empty/null";
	private MockHttpServletRequest request;
	private AuthorizationController  authorizationController;
	
	@BeforeEach
	void setUp() {
		request = new MockHttpServletRequest();
		authorizationController = new AuthorizationController();
	}
	
	@Test
	public void shouldReturn200_WhenTokenIsValid() {
		request.addHeader("Authorization", VALID);
		ResponseEntity<String> response = authorizationController.validateAuthToken(request);
		validateResponse(response, VALID,HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturn400_WhenTokenIsEmpty() {
		request.addHeader("Authorization", "");
		ResponseEntity<String> response = authorizationController.validateAuthToken(request);
		validateResponse(response, EMPTY, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void shouldReturn400_WhenTokenIsNull() {
		ResponseEntity<String> response = authorizationController.validateAuthToken(request);
		validateResponse(response, EMPTY, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void shouldReturn400_WhenTokenIsInvalid() {
		request.addHeader("Authorization", INVALID);
		ResponseEntity<String> response = authorizationController.validateAuthToken(request);
		validateResponse(response, INVALID, HttpStatus.BAD_REQUEST.value());
	}
	
	private void validateResponse(ResponseEntity<String> response, String message, int statusCode) {
		assertThat(response.getBody()).isEqualTo(message);
		assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
	}
}
