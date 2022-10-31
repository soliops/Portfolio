package com.tj.shopping.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
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
			@RequestParam("goodcode") String goodcode,
			@RequestParam("price") String price,
			HttpServletRequest req,
			HttpServletResponse resp
			) throws Exception{
		try {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			Map<String, String> resultMap = new HashMap<String, String>();
			req.setCharacterEncoding("UTF-8");
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = req.getParameterNames();
			String temp = "";
			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, req.getParameter(temp));
			}
			System.out.println("paramMap : "+ paramMap.toString());
			if("0000".equals(paramMap.get("resultCode"))){
				System.out.println("####인증성공/승인요청####");
		
				String mid 		= paramMap.get("mid");
				String timestamp= SignatureUtil.getTimestamp();
				String charset 	= "UTF-8";
				String format 	= "JSON";
				String authToken= paramMap.get("authToken");
				String authUrl	= paramMap.get("authUrl");
				String netCancel= paramMap.get("netCancelUrl");	
				String merchantData = paramMap.get("merchantData");

				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				String signature = SignatureUtil.makeSignature(signParam);

				Map<String, String> authMap = new Hashtable<String, String>();
				authMap.put("mid"			,mid);			// 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		,charset);		// default=UTF-8
				authMap.put("format"		,format);	    // default=XML
				HttpUtil httpUtil = new HttpUtil();
				m.addAttribute("list",completDTO);
				m.addAttribute("payDate",completService.calDate(completDTO.getRegDate()));
				m.addAttribute("item",completService.getItem(completDTO.getProduct_code()));
				try{

					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);

					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
				
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
					
				} catch (Exception ex) {
					System.out.println(ex);
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)
					out.println("## 망취소 API 결과 ##");
					// 취소 결과 확인
					out.println("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
				}

			}else{
				
				resultMap.put("resultCode", paramMap.get("resultCode"));
				resultMap.put("resultMsg", paramMap.get("resultMsg"));
			}

		}catch(Exception e){
			System.out.println(e);
		}

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
