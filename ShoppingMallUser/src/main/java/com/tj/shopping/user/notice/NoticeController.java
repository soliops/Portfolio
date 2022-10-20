package com.tj.shopping.user.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="notice",method=RequestMethod.GET)
	public String NoticePage(
			NoticeDTO dto,
			Model model
			) {
		System.out.println(noticeService);
		List<NoticeDTO> list = noticeService.getList(dto.getSearch());
		System.out.println("공지 : "+list);
		model.addAttribute("NoticeList",list);
		return "user/notice/notice";
	}
}
