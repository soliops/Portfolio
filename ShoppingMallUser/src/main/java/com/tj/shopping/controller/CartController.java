package com.tj.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	
	@GetMapping("cart")
	public String getCartPage(
			Model model
			) {
		List<CartDTO> list = cartService.getCartList();
		model.addAttribute("list",list);
		return "user/cart/ordercart";
	}
	
	@Transactional
	@ResponseBody
	@PostMapping("zzim")
	public String postZzim(
			@RequestParam String check,
			@RequestParam Integer type
			){
		String msg= "";
		if(type == 1) {
			CartDTO dto = cartService.getItem(check);
			cartService.InsertCart(dto);
			msg="success";
		} 
		else {
			cartService.deleteCart(check);
			msg="delete";
		}
		System.out.println(msg);
		return msg;
	}
}
