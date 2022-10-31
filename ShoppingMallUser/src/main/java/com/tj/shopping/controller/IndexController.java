package com.tj.shopping.controller;

import java.util.List;

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
			Model m) throws Exception{
		List<ItemDTO> item = itemService.getList(cate);
		List<ItemDTO> newItem = itemService.getNewList();
		List<ItemDTO> bestItem = itemService.getBestList();
		m.addAttribute("item",item);
		m.addAttribute("newItem",newItem);
		m.addAttribute("bestItem",bestItem);
		m.addAttribute("code",cate);
		return "user/index/index";
	}
}
