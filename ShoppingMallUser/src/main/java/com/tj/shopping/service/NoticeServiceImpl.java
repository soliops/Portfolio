package com.tj.shopping.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.NoticeDTO;
import com.tj.shopping.persistence.NoticeMapper;


@Service
@Primary
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper NoticeMapper;

	
	@Override
	public List<NoticeDTO> getSearchList(String search) {
		System.out.println("????");
		return NoticeMapper.getSearchList(search);
	}

	@Override
	public List<NoticeDTO> getNoticeList(String p_check,String search,int startpage, int pageview) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("check", p_check);
		map.put("search",search);
		map.put("startpage", startpage);
		map.put("pageview", pageview);
		return NoticeMapper.getNoticeList(map);
	}

	@Override
	public Integer getCount() {
		return NoticeMapper.getcount();
	}


}
