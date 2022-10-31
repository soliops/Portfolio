package com.tj.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tj.shopping.domain.CartDTO;
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
	@RequestMapping(value="del",method=RequestMethod.POST, consumes="application/json",
	produces="application/json;charset=UTF-8")
	public  Map<String,String> postCartPage(
			@RequestBody Map<String, String[]> check
			) {
		Map<String, String> list = new HashMap<String, String>();
		List<CartDTO> checkCart = new ArrayList<CartDTO>(); 
		boolean ck = false;
		for(int c=0;c<check.get("check").length;c++) {
			cartService.deleteCart(check.get("check")[c]);
			checkCart.add(cartService.selectCart(check.get("check")[c]));
			ck = checkCart.get(c) == null ? false : true;
		}
		if(ck==false) {
			list.put("result", "success");
		}
		else {
			list.put("result", "fail");
		}
		return list;
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
			List<CartDTO> list = cartService.getCart(check);
			if(list.size()>0) {
				msg="fail";
			}
			else {
				CartDTO dto = cartService.getItem(check);
				cartService.InsertCart(dto);
				msg="success";				
			}
		} 
		else {
			cartService.deleteCart(check);
			msg="delete";
		}
		return msg;
	}
}
