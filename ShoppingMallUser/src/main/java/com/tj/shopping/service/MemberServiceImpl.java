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
	public void createMember(MemberDTO dto)throws Exception{
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dto.setRegdate(datetime.format(dateFormat));
		dto.setOutdate(datetime.format(dateFormat));
		dto.setIdsave("N");
		dto.setMpoint("0");
		dto.setLevel("1");
		dto.setMcount("0");
		memberMapper.insertMember(dto);
	}

	@Override
	public MemberDTO selectId(String mid) {
		return memberMapper.selectId(mid);
	}
	
	
	
}
