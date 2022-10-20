package com.tj.shopping.user.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper NoticeMapper;

	@Override
	public List<NoticeDTO> getList(String search) {
		return NoticeMapper.getList(search);
	}

	@Override
	public List<NoticeDTO> searchList(String pgno, int total, String cate, String search, String check) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
