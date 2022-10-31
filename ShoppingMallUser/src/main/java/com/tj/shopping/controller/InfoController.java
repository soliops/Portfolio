package com.tj.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tj.shopping.domain.InfoDTO;
import com.tj.shopping.service.InfoService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class InfoController {
	
	private final InfoService infoService;
	
	@GetMapping("company")
	public String getCompanyPage(
			Model model
			) {
		InfoDTO dto = infoService.getInfo();
		model.addAttribute("info",dto);
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
