package com.tj.shopping.user.cs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CSController {
	
	@Autowired
	private CSService csService;
	
	@RequestMapping(value="cs",method=RequestMethod.GET)
	public String CSPage(
			@RequestParam(name="cate",defaultValue = "") String cate,
			Model model,HttpServletRequest req
			) {		 
		List<CSDTO> list =null;
		if(cate.equals("") || cate==null) {
			list = csService.getList();
		
		}else {
			list = csService.selectList(cate);
		}
		model.addAttribute("list",list);
		return "user/cs/cs";
	}
}