package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailOtp;
import com.example.demo.repository.EmailOtpRepository;

@Service
public class EmailService {
	@Autowired
	private EmailOtpRepository emailOtpRepository;

	public String addEmailOtp(EmailOtp emailOtp) {
		if(emailOtpRepository.save(emailOtp)!=null) {
			return "Email Otp Saved Successfully";
		}
		else
			return "something went wrong";
	}
	
	public EmailOtp getEmailOtpByEmail(String email) {
		List<EmailOtp> obj1=emailOtpRepository.findByEmail(email);
		EmailOtp obj=obj1.get(obj1.size()-1);
		return obj;
	}
}
