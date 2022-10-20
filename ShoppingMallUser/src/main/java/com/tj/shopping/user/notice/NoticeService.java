package com.tj.shopping.user.notice;

import java.util.List;

public interface NoticeService {
	public List<NoticeDTO> getList(String search);
	public List<NoticeDTO> searchList(String pgno, int total, String cate, String search,String check);
	public Integer getCount();
}
