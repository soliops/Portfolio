package com.tj.shopping.service;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.LogDTO;
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
		return  loginMapper.getId(loginDTO);
	}
	
	@Override
	public String Hashing(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		password = byteToHex(md.digest());
		return password;
	}
	
	private String byteToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
	@Override
	public void insertHistory(String mid){
		LogDTO log = new LogDTO();
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.setMid(mid);
		log.setDate(dateFormat.format(now));
		loginMapper.insertHistory(log);
	}
	
	
}
