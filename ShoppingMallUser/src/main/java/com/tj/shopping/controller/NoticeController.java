package com.tj.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.shopping.domain.NoticeDTO;
import com.tj.shopping.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="notice",method=RequestMethod.GET)
	public String NoticePage(
			NoticeDTO dto,
			@RequestParam(name="page",defaultValue = "1") String pgno,
			@RequestParam(name="cate",defaultValue = "0") String cate,
			@RequestParam(name="search",defaultValue = "") String search,
			@RequestParam(name="p_check",defaultValue = "N") String check,
			Model model
			) {
		List<NoticeDTO> list = search==""? noticeService.getSearchList(search) : noticeService.getList();
		System.out.println("공지 : "+list);
		model.addAttribute("NoticeList",list);
		return "user/notice/notice";
	}
}
