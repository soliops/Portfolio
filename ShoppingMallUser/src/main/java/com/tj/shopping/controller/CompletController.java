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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.service.CompletService;
@Controller
public class CompletController {
	@Autowired
	CompletService completService;

	private CompletDTO completDTO;
	
	@RequestMapping(value="complet", method = RequestMethod.POST)
	public String completPage(
			Model m,
			@ModelAttribute("list") CompletDTO completDTO,
			HttpServletRequest req,
			HttpServletResponse resp
			) throws Exception{
		try {
			System.out.println(completDTO);
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
//			System.out.println("paramMap : "+ paramMap.toString());
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
				String TotPrice = paramMap.get("TotPrice");
				String goodName = paramMap.get("goodName");
				String paymethod = paramMap.get("payMethod");
				String applDate = paramMap.get("applDate");
				String applTime = paramMap.get("applTime");
				
				String buyerName = paramMap.get("buyerName");
				String buyerTel = paramMap.get("buyerTel");
				String buyerEmail = paramMap.get("buyerEmail");
				
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
	
	@RequestMapping(value="pay", method = RequestMethod.POST)
	public String payPage(
			Model m,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		Map<String, String> resultMap = new HashMap<String, String>();
		response.setContentType("text/html;charset=utf-8");
		try{
			System.out.println(this.completDTO);
			PrintWriter out = response.getWriter();
			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			request.setCharacterEncoding("UTF-8");

			Map<String,String> paramMap = new Hashtable<String,String>();

			Enumeration elems = request.getParameterNames();

			String temp = "";

			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();

				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
			
			
			if("0000".equals(paramMap.get("resultCode"))){

				System.out.println("####인증성공/승인요청####");

				//############################################
				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				//############################################
				
				String mid 		= paramMap.get("mid");
				String timestamp= SignatureUtil.getTimestamp();
				String charset 	= "UTF-8";
				String format 	= "JSON";
				String authToken= paramMap.get("authToken");
				String authUrl	= paramMap.get("authUrl");
				String netCancel= paramMap.get("netCancelUrl");	
				String merchantData = paramMap.get("merchantData");
				System.out.println("제대로 왔나2-----------"+merchantData);
				//#####################
				// 2.signature 생성
				//#####################
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);


				//#####################
				// 3.API 요청 전문 생성
				//#####################
				Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			,mid);			// 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		,charset);		// default=UTF-8
				authMap.put("format"		,format);	    // default=XML


				HttpUtil httpUtil = new HttpUtil();

				try{
					//#####################
					// 4.API 통신 시작
					//#####################

					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//############################################################
					//5.API 통신결과 처리(***가맹점 개발수정***)
					//############################################################
					
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
								
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
//					System.out.println("결과 : "+resultMap);
					CompletDTO dto = completService.setCompletDTO(resultMap);
					System.out.println(dto);
				  // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

				  //throw new Exception("강제 Exception");
				} catch (Exception ex) {

					//####################################
					// 실패시 처리(***가맹점 개발수정***)
					//####################################

					//---- db 저장 실패시 등 예외처리----//
					System.out.println(ex);

					//#####################
					// 망취소 API
					//#####################
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
	
	@RequestMapping("close")
	public String closePage() {
		return "user/order/close";
	}
	
	@ResponseBody
	@PostMapping("/stock")
	public String checkStock(
			@RequestBody CompletDTO completDTO
			) {
		this.completDTO = completDTO;
		System.out.println(this.completDTO);
		String msg="";
		return msg;
	}
}
