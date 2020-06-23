package com.katta.SpringBootDemo.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/v1", produces = "application/json")
@RestController
public class SpringBootDemoContoller {

	@RequestMapping(method = RequestMethod.GET, value = "/helloworld")
	public ResponseEntity<String> getVehicleServiceHistory() {

		return new ResponseEntity<>("Hello World", HttpStatus.OK);
	}

}
