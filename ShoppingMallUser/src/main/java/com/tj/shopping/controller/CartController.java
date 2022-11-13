package com.tj.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
	
	@PostMapping("cart")
	public String getCartPage(
			@RequestParam(name = "mid",defaultValue = "") String mid,
			HttpServletRequest req,
			Model model
			)throws Exception {
		Cookie[] cookie = req.getCookies();
		if(mid==""||mid==null){
			for(int i=0; cookie!=null&&i<cookie.length; i++) {			
				if(cookie[i].getName().equals("mid")) {
					mid = cookie[i].getValue();
				}
			}
		}
		List<CartDTO> list = cartService.getCartList(mid);
		model.addAttribute("list",list);
		return "user/cart/ordercart";
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="del",method=RequestMethod.POST, consumes="application/json",
	produces="application/json;charset=UTF-8")
	public  Map<String,String> postCartPage(
			@RequestBody Map<String,String[]> check,
			HttpServletRequest req
			) {
		Map<String, String> list = new HashMap<String, String>();
		List<CartDTO> checkCart = new ArrayList<CartDTO>(); 
		boolean ck = false;
		Cookie[] cookie = req.getCookies();
		for(int c=0;c<check.get("check").length;c++) {
			String[] data = check.get("check")[c].split("=");
			if(data[0]==""||data[0]==null){
				for(int i=0; cookie!=null&&i<cookie.length; i++) {			
					if(cookie[i].getName().equals("mid")) {
						data[0] = cookie[i].getValue();
					}
				}
			}
			cartService.deleteCart(data[1],data[0]);
			checkCart.add(cartService.selectCart(data[1],data[0]));
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
			@RequestParam("check") String check,
			@RequestParam("type") Integer type,
			@RequestParam("mid") String mid,
			HttpServletRequest req
			)throws Exception{
		String msg= "";	
		Cookie[] cookie = req.getCookies();
		boolean idcheck = true;
		if(mid==""||mid==null){
			for(int i=0; cookie!=null&&i<cookie.length; i++) {			
				if(cookie[i].getName().equals("mid")) {
					mid = cookie[i].getValue();
					idcheck=false;
				}
			}
		}
		
		if(type == 1) {
			List<CartDTO> list = cartService.getCart(check,mid);
			if(list.size()==0) {
				CartDTO dto = cartService.getItem(check);
				if(idcheck==true) {dto.setId_use("Y");}
				dto.setMid(mid);
				cartService.InsertCart(dto);
				msg="success";				
			}
			else {
				msg="fail";
			}
		} 
		else {
			cartService.deleteCart(check,mid);
			msg="delete";
		}
		return msg;
	}
}
