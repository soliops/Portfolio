package com.tj.shopping.service;

import java.util.List;

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
	public List<NoticeDTO> getList() {
		return NoticeMapper.getList();
	}
	
	@Override
	public List<NoticeDTO> getSearchList(String search) {
		return NoticeMapper.getSearchList(search);
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
