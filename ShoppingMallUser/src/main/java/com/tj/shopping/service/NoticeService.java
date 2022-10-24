package com.tj.shopping.service;

import java.util.List;

import com.tj.shopping.domain.NoticeDTO;

public interface NoticeService {
	public List<NoticeDTO> getSearchList(String search);
	public List<NoticeDTO> getNoticeList(String p_check,String search,int startpage, int pageview);
	public Integer getCount();
}
