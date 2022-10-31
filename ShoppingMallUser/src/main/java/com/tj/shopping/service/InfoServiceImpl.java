package com.tj.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.InfoDTO;
import com.tj.shopping.persistence.InfoMapper;

@Service
@Primary
public class InfoServiceImpl implements InfoService {
	@Autowired
	InfoMapper infoMapper;
	
	@Override
	public InfoDTO getInfo() {
		return infoMapper.getInfo();
	}

}
