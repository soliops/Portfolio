package com.tj.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.persistence.MemberMapper;

@Service
@Primary
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	
	
}
