package com.tj.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value= {"/","/index","/index.do","/index.html"})
	public String IndexPage(Model m, HttpServletRequest req) {
		
		return "user/index/index";
	}
}
