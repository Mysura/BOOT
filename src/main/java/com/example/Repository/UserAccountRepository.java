package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
	
	UserAccount findByUserName(String userName);

}
