package com.tj.shopping.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tj.shopping.domain.MemberDTO;
import com.tj.shopping.service.MemberService;

@Controller
public class MemberConroller {
	PrintWriter pr = null; 
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="member",method=RequestMethod.GET)
	public String getMemberPage(
			) {
		return "user/member/member";
	}
	
	@RequestMapping(value="member",method=RequestMethod.POST)
	public void postMemberPage(
			MemberDTO memberDTO,
			HttpServletResponse resp
			) throws Exception{
		resp.setContentType("text/html; charset=UTF-8");
		try {
			this.pr = resp.getWriter();
			String pass = memberService.Hashing(memberDTO.getMpassword()); 
			memberDTO.setMpassword(pass);
			memberService.createMember(memberDTO);			
			pr.write("<script>alert('회원 등록이 완료되었습니다.'); location.href='./index';</script>");
		} catch (Exception e) {
			String error = e.getMessage(); 
			this.pr = resp.getWriter(); 
			pr.write("<script>alert('회원 등록을 실패했습니다.');</script>");
		}

	}
	
	@RequestMapping(value="/idcheck",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Boolean> membercheck(
			@RequestParam("mid") String mid
			)throws Exception{
		Boolean check = memberService.selectId(mid)!=null ? false : true;		
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		map.put("sign",check);
		return map;
	}
}
