package com.tj.shopping.service;

import com.tj.shopping.domain.MemberDTO;


public interface MemberService {

	public void createMember(MemberDTO memberDTO)throws Exception;
	public String Hashing(String password) throws Exception;
	public MemberDTO selectId(String mid);
}
