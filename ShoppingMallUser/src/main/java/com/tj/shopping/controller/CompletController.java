package com.tj.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inicis.std.util.SignatureUtil;
import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.service.CompletService;
@Controller
public class CompletController {
	@Autowired
	CompletService completService;

	@RequestMapping(value="complet", method = RequestMethod.POST)
	public String completPage(
			Model m,
			CompletDTO completDTO,
 			HttpServletRequest req
			) throws Exception{
		/*결제자 정보
		 결제자명, 휴대폰번호 ("-"뻬고), 이메일주소
		 상품명, 주문번호(4~9자리),최종 결제 금액(숫자만)
		 상품갯수, 회원(아이디), 비회원(휴대폰)
		 
		 이니시스 기준()
		 CARD,DirectBank()
		 */
		System.out.println(completDTO);
//		String price = req.getParameter("price");
//		String price = req.getParameter("oid");
//		String price = req.getParameter("price");

		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		String mKey = SignatureUtil.hash(signKey, "SHA-256");
		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	
		String version = req.getParameter("version");
		//이니시스 제공 코드
		Map<String, String> signParam = new HashMap<String, String>();
		signParam.put("oid", orderNumber);
		signParam.put("mKey", mKey);
		signParam.put("timestamp", timestamp);
		String signature = SignatureUtil.makeSignature(signParam);
		m.addAttribute("version",version);
		m.addAttribute("signature",signature);
		m.addAttribute("list",completDTO);
		m.addAttribute("payDate",completService.calDate(completDTO.getRegDate()));
		m.addAttribute("item",completService.getItem(completDTO.getProduct_code()));
		return "user/order/complet";
	}
	
}
