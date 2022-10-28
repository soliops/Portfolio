package com.tj.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.domain.OrderDTO;
import com.tj.shopping.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="order",method=RequestMethod.GET)
	public String getOrderPage() {
		return "user/order/order";
	}

	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String postOrderPage(
			OrderDTO orderDTO,
			Model model
			) {
		String code = orderDTO.getProduct_code();
		ItemDTO item = orderService.getProduct(code);
		model.addAttribute("list",orderDTO);
		model.addAttribute("item",item);
		String number = orderService.getNumber();
		String regDate=orderService.getDate();
		String modDate=orderService.getDate();		
		model.addAttribute("orderNumber",number);
		model.addAttribute("regDate",regDate);
		model.addAttribute("modDate",modDate);		
		return "user/order/order";
	}
}
