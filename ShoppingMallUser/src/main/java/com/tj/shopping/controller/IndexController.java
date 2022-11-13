package com.tj.shopping.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final ItemService itemService;
	
	@RequestMapping(value= {"/","/index","/index.do","/index.html"})
	public String IndexPage(
			@RequestParam(name="cate",defaultValue = "1") String cate,
			Model m,
			HttpServletRequest req,
			HttpServletResponse resp
			) throws Exception{
		List<ItemDTO> item = itemService.getList(cate);
		List<ItemDTO> newItem = itemService.getNewList();
		List<ItemDTO> bestItem = itemService.getBestList();
		Cookie[] cookie = req.getCookies();
		String mid ="";
		for(int i=0; cookie!=null&&i<cookie.length; i++) {			
			if(cookie[i].getName().equals("mid")) {
				mid = cookie[i].getValue();
			}
		}
		if(mid==""||mid==null) {
			mid= String.valueOf((int)(Math.random()*1000000000));
			Cookie cookies = new Cookie("mid",mid);
			cookies.setPath("/");
			cookies.setMaxAge(60*60*24*7);
			resp.addCookie(cookies);
		}
		m.addAttribute("item",item);
		m.addAttribute("newItem",newItem);
		m.addAttribute("bestItem",bestItem);
		m.addAttribute("code",cate);
		return "user/index/index";
	}
}
