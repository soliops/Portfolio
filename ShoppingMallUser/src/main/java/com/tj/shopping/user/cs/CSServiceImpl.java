package com.tj.shopping.user.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Primary
public class CSServiceImpl implements CSService {

	@Autowired
	private CSMapper csMapper;
	
	@Override
	public List<CSDTO> getList() {
		return csMapper.getFAQ();
	}

	@Override
	public List<CSDTO> selectList(String cate) {
		return csMapper.selectFAQ(cate);
	}

	
}
