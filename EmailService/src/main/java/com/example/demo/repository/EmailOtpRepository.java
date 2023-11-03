package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmailOtp;


@Repository
public interface EmailOtpRepository extends JpaRepository <EmailOtp,Integer>{

	//custom finder method
	public List<EmailOtp> findByEmail(String email);
}
