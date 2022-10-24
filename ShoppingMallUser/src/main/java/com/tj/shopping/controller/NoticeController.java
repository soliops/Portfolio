package com.tj.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			@RequestParam(name="page",defaultValue = "1") String pgno,
			@RequestParam(name="category",defaultValue = "0") String cate,
			@RequestParam(name="search_text",defaultValue = "") String search,
			@RequestParam(name="p_check", defaultValue = "N") String p_check,
			Model model
			) {
		int pageview = 10;
		int startpage=((Integer.parseInt(pgno)-1))*pageview;
		double pagenumber = 1;
		Integer total = noticeService.getCount();
		pagenumber = total%pageview==0 ? total/pageview : (total/pageview)+1;
		List<NoticeDTO> list = null;
		List<NoticeDTO> searchlist = null;
		list = noticeService.getSearchList(search);			
		searchlist = noticeService.getNoticeList(p_check,search,startpage,pageview);
		model.addAttribute("total",total);
		model.addAttribute("NoticeList",list);
		model.addAttribute("searchlist",searchlist);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("page", pgno);
		map.put("pageview", pageview);
		map.put("startpage", startpage);
		map.put("pagenumber", pagenumber);
		map.put("cate", cate);
		map.put("search", search);
		map.put("check",p_check);
		model.addAttribute("map",map);
		return "user/notice/notice";
	}
}
