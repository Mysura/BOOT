package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.UserAccountRepository;
import com.example.model.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	
	@Autowired
	public UserAccountRepository userAccRep;
	
	@Override
	public UserAccount findByUserName(String userName) {
		return userAccRep.findByUserName(userName);
	}
	
	

}
