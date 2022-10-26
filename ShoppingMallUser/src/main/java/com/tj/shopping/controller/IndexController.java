package com.tj.shopping.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			Model m, HttpServletResponse resp) throws Exception{
		resp.setContentType("text/html; charset=UTF-8");
		List<ItemDTO> item = itemService.getList(cate);
		PrintWriter pr = resp.getWriter();		
		m.addAttribute("item",item);
		Map<String,List<ItemDTO>> map = new HashMap<String,List<ItemDTO>>();
		map.put("item", item);
		pr.print("ok");
		m.addAttribute("code",cate);
		return "user/index/index";
	}
}
