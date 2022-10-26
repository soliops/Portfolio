package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
	
	@GetMapping("company")
	public String getCompanyPage() {
		return "user/info/company";
	} 
	
	@GetMapping("guide")
	public String getGuidePage() {
		return "user/info/guide";
	}
	
	@GetMapping("agreement")
	public String getAgreePage() {
		return "user/info/agreement";
	}
	
	@GetMapping("privacy")
	public String getPrivacyPage() {
		return "user/privacy/privacy";
	}
}
