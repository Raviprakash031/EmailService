package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.EmailSenderService;
import com.example.demo.model.EmailOtp;
import com.example.demo.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/emailService")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping("/sendOtp/{email}")
	public String sendOtp(@PathVariable String email) throws MessagingException {
		String otp=generateOtp();
		String to=email;
		String subject="This otp transaction for you";
		String body="Here is your otp"+otp;
		emailSenderService.sendEmail(to, subject, body);
		EmailOtp obj=new EmailOtp();
		obj.setEmail(email);
		obj.setOtp(otp);
		String result=emailService.addEmailOtp(obj);
		return "Otp send successfully";
	}
	
	@RequestMapping("/verifyOtp")
	public String verifyOtp(@RequestBody EmailOtp emailOtp) {
		String email=emailOtp.getEmail();
		String otp=emailOtp.getOtp();
		EmailOtp obj=emailService.getEmailOtpByEmail(email);
		if(obj.getOtp().equals(otp))
			return "OTP Verified Successfully";
		else
			return "OTP did not match";
		
	}
	public static String generateOtp() {
		String num="123456789";
		Random obj=new Random();
		char[] ch=new char[6];
		for(int i=0;i<ch.length;i++) {
			ch[i]=num.charAt(obj.nextInt(num.length()));
		}
		String otp=new String(ch);
		return otp;
	}

}
