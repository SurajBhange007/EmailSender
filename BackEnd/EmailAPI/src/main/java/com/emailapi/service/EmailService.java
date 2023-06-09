package com.emailapi.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${mail.host}")
	private String mailHost;
	
	@Value("${mail.user.username}")
	private String username;
	@Value("${mail.user.password}")
	private String password;
		
	public boolean sendEmail(String subject, String message, String to) {
		boolean isSent=false;
		//setting host data
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", mailHost);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.auth", true);
		System.out.println(mailHost);
		
		//get session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		
		//compose the message 
		MimeMessage m = new MimeMessage(session);
		try {
			
			m.setFrom(new InternetAddress(username));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			//send mail
			Transport.send(m);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
}
