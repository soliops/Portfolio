package com.tj.shopping.service;

import java.util.List;

import com.tj.shopping.domain.CSDTO;

public interface CSService {
	public List<CSDTO> getList();
	public List<CSDTO> selectList(String cate);
}
