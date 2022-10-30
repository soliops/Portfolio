package com.tj.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="item",method=RequestMethod.GET)
	public String ItemPage(
			@RequestParam(name = "cate",defaultValue = "1") String cate,
			@RequestParam(name = "num", defaultValue = "1") Integer num,
			@RequestParam(name ="product", defaultValue = "") String product,
			Model model,
			HttpServletRequest req
			) {		
		ItemDTO list = null;
		if(product != "") {
			list = itemService.getProduct(product);
		}
		else {
			list = itemService.getItem(num);						
		}
		model.addAttribute("list",list);
		return "user/item/item";
	}
}
