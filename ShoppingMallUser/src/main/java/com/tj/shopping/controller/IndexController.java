package com.tj.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
			@RequestParam(name="code",defaultValue = "1") String code,
			Model m, HttpServletRequest req) {
		List<ItemDTO> item = itemService.getList(code);
		m.addAttribute("item",item);
		return "user/index/index";
	}
	
}
