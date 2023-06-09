package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.emailapi.model.EmailRequest;
import com.emailapi.model.EmailResponse;
import com.emailapi.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest ) {
		boolean isSentEmail = this.emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage()
				, emailRequest.getto());
		if(isSentEmail) {
			return ResponseEntity.ok(new EmailResponse("Email Sent Successfully..."));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Not sent..."));
		}
	}
}
