package com.tj.shopping.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
//			@RequestParam("product_nm") String product_nm,
//			@RequestParam("product_dtc") String product_dtc,
//			@RequestParam("price") String price,
//			@RequestParam("point") String point,
//			@RequestParam("total") String total,
//			@RequestParam("product_ea") String product_ea,
			OrderDTO orderDTO,
			Model model

			) {
		
//		DecimalFormat dcf = new DecimalFormat("###,###");
//		orderDTO.setProduct_price(dcf.format(Integer.parseInt(orderDTO.getProduct_price())));
//		System.out.println(orderDTO.getProduct_price());
		String code = orderDTO.getProduct_code();
		ItemDTO item = orderService.getProduct(code);
		model.addAttribute("list",orderDTO);
		model.addAttribute("item",item);
//		model.addAttribute("product_nm",product_nm);
//		model.addAttribute("product_dtc",product_dtc);
//		model.addAttribute("price",dcf.format(Integer.parseInt(price)));
//		model.addAttribute("point",point);
//		model.addAttribute("total2",total); //실제 결제금액
//		model.addAttribute("total",dcf.format(Integer.parseInt(total)));
//		model.addAttribute("product_ea",product_ea);

		return "user/order/order";
	}
}
