package com.example.service;

import com.example.model.UserAccount;

public interface UserAccountService {
	
	UserAccount findByUserName(String userName);

}
