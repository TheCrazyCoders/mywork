package com.techsophy.vps.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

//import com.techsophy.vps.utils.JavaEmail;

@Service
public class JavaEmailService  {
	@Autowired
	private JavaMailSender javaEmail;

	public void createEmailMessage(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
		simpleMailMessage.setTo(to); 
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		javaEmail.send(simpleMailMessage);
	}


	public void attachInMail(ResponseEntity<byte[]> attachment, String  toMail) throws MessagingException {
		MimeMessage message = javaEmail.createMimeMessage(); 
	
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
         helper.setTo(toMail);
         helper.setSubject("Appointment Summary");
         helper.setText("Your Appointment created");
         helper.addAttachment("MyTestFile.pdf", new ByteArrayResource(attachment.getBody()));
         javaEmail.send(message);
		
		
		
	}
	


    

}