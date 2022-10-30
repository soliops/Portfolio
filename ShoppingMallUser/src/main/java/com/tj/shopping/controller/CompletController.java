package com.tj.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.CompletDTO;
@Controller
public class CompletController {

	@RequestMapping(value="complet", method = RequestMethod.POST)
	public String completPage(
			Model m,
			CompletDTO completDTO,
			@RequestParam("goodcode") String goodcode,
			@RequestParam("price") String price,
	
			HttpServletRequest req
			) throws Exception{
		
		
		return "user/order/complet";
	}
	
	@RequestMapping("pay")
	public String payPage() {
		return "user/order/INIstdpay_pc_return";
	}
	
	@RequestMapping("close")
	public String closePage() {
		return "user/order/close";
	}
	
}
