package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
	@GetMapping("cart")
	public String getCartPage() {
		return "user/cart/ordercart";
	}
	
	@PostMapping("cart")
	public String postCartPage(
			
			) {
		
		return "user/cart/ordercart";
	}
}
