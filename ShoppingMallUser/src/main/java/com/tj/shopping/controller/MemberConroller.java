package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tj.shopping.domain.MemberDTO;

@Controller
public class MemberConroller {
	
	@RequestMapping(value="member",method=RequestMethod.GET)
	public String getMemberPage(
			) {
		return "user/member/member";
	}
	
	@RequestMapping(value="member",method=RequestMethod.POST)
	public String postMemberPage(
			) {
		return "user/member/member";
	}
}
