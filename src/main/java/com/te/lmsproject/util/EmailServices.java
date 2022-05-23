package com.te.lmsproject.util;


	import java.util.UUID;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.mail.SimpleMailMessage;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.stereotype.Service;

	@Service
	public class EmailServices {

		@Autowired
		JavaMailSender mailSender;
		

		public String sendPassword(String to) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("megha.s@testyantra.com");
			message.setTo(to);
			message.setSubject("Auto Generated Password");
			UUID uuid = UUID.randomUUID();
			message.setText("The password ==> "+uuid);
			this.mailSender.send(message);
			return ""+uuid;				
		}
	


}
