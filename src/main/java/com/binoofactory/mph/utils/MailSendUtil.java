package com.binoofactory.mph.utils;

import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSendUtil {
	
	private JavaMailSenderImpl mailSender;

	@Value("${user.mail.send.address}")
	public String sender;
	
	@Value("${spring.mail.host}")
	public String host;
	@Value("${spring.mail.port}")
	public String port;
	@Value("${spring.mail.username}")
	public String username;
	@Value("${spring.mail.password}")
	public String password;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	public String auth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	public String starttls;
	
	public void mailSend(Map<String, String> dataMap) throws MessagingException 
	{		
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(Integer.parseInt(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", auth);
		properties.setProperty("mail.smtp.starttls.enable", starttls);
		mailSender.setJavaMailProperties(properties);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		mimeMessage.setContent(dataMap.get("CONTENTS"), "text/html");
		messageHelper.setTo(dataMap.get("TARGET_ADDRESS"));
		messageHelper.setFrom(sender);
		messageHelper.setSubject(dataMap.get("TITLE"));
		messageHelper.setText(dataMap.get("CONTENTS"), true);
		
		
		mailSender.send(message);
	}
}
