package com.tj.shopping.controller;


import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.CartDTO;
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

	@Transactional
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String postOrderPage(
			OrderDTO orderDTO,
			@RequestParam(name="ship_total",defaultValue = "0")  String ship_total,
			@RequestParam(name="agree",defaultValue = "0") String agree,
 			Model model
			) throws Exception{
		
		String[] ar = null;
		String ch = "N";
		if(!agree.equals("0")) {
			ar = agree.split(",");
			ch = "Y";
		}
		List<CartDTO> select  = orderService.selectCart(Integer.toString(orderDTO.getProduct_idx()));
		if(select.size()==0) {
			orderService.InsertCart(orderDTO,ch);
		}
		List<CartDTO> cart = orderService.getProduct(orderDTO.getProduct_code(),orderDTO.getShip_pay(),orderDTO.getProduct_ea());		
		/*결제자 정보
		 결제자명, 휴대폰번호 ("-"뻬고), 이메일주소
		 상품명, 주문번호(4~9자리),최종 결제 금액(숫자만)
		 상품갯수, 회원(아이디), 비회원(휴대폰)
		 
		 이니시스 기준()
		 CARD,DirectBank()
		 */
		
		String mid					= "INIpayTest";		  					
		String orderNumber			= orderService.orderNumber(mid);	
		Map<String,String> list = orderService.getlist(orderDTO,orderNumber);

		list.put("mid",mid);
		model.addAttribute("list",list);
		model.addAttribute("cart",cart);
		
		String regDate=orderService.getDate();
		String modDate=orderService.getDate();	
		
		model.addAttribute("orderNumber",orderNumber.replace("INIpayTest_", ""));
		model.addAttribute("regDate",regDate);
		model.addAttribute("modDate",modDate);		
		return "user/order/order";
	}
}
