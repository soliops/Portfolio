package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inicis.std.util.SignatureUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
@Controller
public class CompletController {

	@RequestMapping(value="complet", method = RequestMethod.POST)
	public String completPage(
			Model m,
			String cname,
			String chp[],
			String ctel[],
			String person_post,
			String person_addr,
			String person_addrdtc,
			String person_phone[],
			HttpServletRequest req
			) throws Exception{
		/*결제자 정보
		 결제자명, 휴대폰번호 ("-"뻬고), 이메일주소
		 상품명, 주문번호(4~9자리),최종 결제 금액(숫자만)
		 상품갯수, 회원(아이디), 비회원(휴대폰)
		 
		 이니시스 기준()
		 CARD,DirectBank()
		 */
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	
		
		String price = req.getParameter("price"); 
		String payment = req.getParameter("payment");
		String version = req.getParameter("version");
		//이니시스 제공 코드
		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);

		String signature = SignatureUtil.makeSignature(signParam);
		
		m.addAttribute("version",version);
		
		return "user/order/complet";
	}
	
}
