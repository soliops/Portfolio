package com.tj.shopping.service;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.MemberDTO;
import com.tj.shopping.persistence.MemberMapper;

@Service
@Primary
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public String Hashing(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		password = md.digest().toString();
		return password;
	}	

	@Override
	public void createMember(MemberDTO dto)throws Exception{
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dto.setRegdate(datetime.format(dateFormat));
		dto.setOutdate(datetime.format(dateFormat));
		dto.setIdsave("N");
		memberMapper.insertMember(dto);
	}

	@Override
	public MemberDTO selectId(String mid) {
		return memberMapper.selectId(mid);
	}
	
	
	
}
