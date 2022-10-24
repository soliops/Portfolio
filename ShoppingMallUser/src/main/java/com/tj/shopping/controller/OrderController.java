package com.tj.shopping.controller;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
	
	@RequestMapping(value="order",method=RequestMethod.GET)
	public String getOrderPage() {
		return "user/order/order";
	}

	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String postOrderPage(
			@RequestParam("product_nm") String product_nm,
			@RequestParam("product_dtc") String product_dtc,
			@RequestParam("price") String price,
			@RequestParam("point") String point,
			@RequestParam("total") String total,
			@RequestParam("product_ea") String product_ea,
			Model model

			) {
		model.addAttribute("product_nm",product_nm);
		model.addAttribute("product_dtc",product_dtc);
		DecimalFormat dcf = new DecimalFormat("###,###");
		model.addAttribute("price",dcf.format(Integer.parseInt(price)));
		model.addAttribute("point",point);
		model.addAttribute("total2",total); //실제 결제금액
		model.addAttribute("total",dcf.format(Integer.parseInt(total)));
		model.addAttribute("product_ea",product_ea);

		return "user/order/order";
	}
}
