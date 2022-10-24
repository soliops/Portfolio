package com.tj.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.MemberDTO;
import com.tj.shopping.service.MemberService;

@Controller
public class MemberConroller {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="member",method=RequestMethod.GET)
	public String getMemberPage(
			) {
		return "user/member/member";
	}
	
	@RequestMapping(value="member",method=RequestMethod.POST)
	public String postMemberPage(
			MemberDTO memberDTO
			) {
		
		System.out.println(memberDTO);
		return "user/member/member";
	}
	@RequestMapping(value="membercheck",method=RequestMethod.POST)
	public String postMemberCheck(
			@RequestParam("mid") String mid
			) {
		System.out.println(mid);
		return "user/member/member";
	}
}
