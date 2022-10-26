package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
	@GetMapping("cart")
	public String getCartPage() {
		return "user/cart/ordercart";
	}
}
