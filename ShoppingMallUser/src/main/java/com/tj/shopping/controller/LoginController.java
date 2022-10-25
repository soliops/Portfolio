package com.tj.shopping.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.LoginDTO;
import com.tj.shopping.service.LoginService;

@Controller
public class LoginController {
	PrintWriter pr = null; 

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String getLoginPage(
			) {
		
		return "user/login/member_login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public void postLoginPage(
		LoginDTO loginDTO,
		@RequestParam("order_number") String number,
		@RequestParam("order_email") String email,
		HttpServletResponse resp
			)throws Exception{
		resp.setContentType("text/html; charset=UTF-8");
		try {
		String id = loginDTO.getMid();
		this.pr = resp.getWriter();
		LoginDTO check = loginService.getId(loginDTO);
		String result = check==null  ? "false" : check.getMid();
		if(id.equals(result)) {
			this.pr.write("<script>alert('로그인되었습니다.'); location.href='./index';</script>");
		}
		else {
			this.pr.write("<script>alert('로그인을 실패했습니다.');location.href='./login';</script>");
		}
		}catch(Exception e) {
			this.pr = resp.getWriter();
			this.pr.write("<script>alert('로그인 시스템 오류');location.href='./login';</script>");			
		}
	}
}
