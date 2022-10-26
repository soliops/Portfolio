package com.tj.shopping.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println("카테고리 번호"+cate);
		List<ItemDTO> item = itemService.getList(cate);
		PrintWriter pr = resp.getWriter();		
		m.addAttribute("item",item);
		Map<String,List<ItemDTO>> map = new HashMap<String,List<ItemDTO>>();
		map.put("item", item);
		pr.print("ok");
		m.addAttribute("code",cate);
		System.out.println(map);
		return "user/index/index";
	}
	@PostMapping("/")
	@ResponseBody
	public List<ItemDTO> postIndexPage(
			@RequestParam(name="cate") String code
//			Model model
//			 HttpServletResponse resp
			)
//					throws Exception
	{
//		resp.setContentType("text/html; charset=UTF-8");
		Map<String,List<ItemDTO>> map = new HashMap<String,List<ItemDTO>>();
		List<ItemDTO> check = itemService.getList(code);
		check = check!=null ? check : null;
		map.put("item", check);
//		model.addAttribute("item",check);
//		PrintWriter pr = resp.getWriter();	
//		pr.print(map);
		System.out.println(map);
		return check;
	}
	
	@GetMapping("collection")
	public Map<String,List<ItemDTO>> getCollection(
			@RequestParam(name="cate") String code
			){
		System.out.println(code);
		Map<String,List<ItemDTO>> map = new HashMap<String,List<ItemDTO>>();
		List<ItemDTO> check = itemService.getList(code);
		check = check!=null ? check : null;
		map.put("item", check);
		return map;
	}
}
