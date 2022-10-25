package com.tj.shopping.service;

import com.tj.shopping.domain.LoginDTO;

public interface LoginService {

	public LoginDTO getId(LoginDTO loginDTO)throws Exception;
	public String Hashing(String password) throws Exception;

}
