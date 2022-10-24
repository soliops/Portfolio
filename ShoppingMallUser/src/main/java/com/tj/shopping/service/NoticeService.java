package com.tj.shopping.service;

import java.util.List;

import com.tj.shopping.domain.NoticeDTO;

public interface NoticeService {
	public List<NoticeDTO> getList();
	public List<NoticeDTO> getSearchList(String search);
	public List<NoticeDTO> searchList(String pgno, int total, String cate, String search,String check);
	public Integer getCount();
}
