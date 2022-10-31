package com.tj.shopping.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.LoginDTO;
import com.tj.shopping.persistence.LoginMapper;

@Service
@Primary
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginMapper loginMapper;
	
	@Override
	public LoginDTO getId(LoginDTO loginDTO) throws Exception{
		String pass = Hashing(loginDTO.getMpassword());
		loginDTO.setMpassword(pass);
		System.out.println(loginDTO);
		return  loginMapper.getId(loginDTO);
	}

	@Override
	public String Hashing(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		System.out.println("비번"+md);
		md.update(password.getBytes());
		password = md.digest().toString();
		return password;
	}

}
