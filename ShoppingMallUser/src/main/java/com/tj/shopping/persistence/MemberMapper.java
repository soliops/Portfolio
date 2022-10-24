package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.MemberDTO;

@Repository
public interface MemberMapper {

	@Insert("")
	public MemberDTO insertMember(MemberDTO memberDTO); 
	
}
