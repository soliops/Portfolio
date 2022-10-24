package com.tj.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.persistence.ItemMapper;

@Service
@Primary
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemMapper itemMapper;
	
	@Override
	public ItemDTO getItem(Integer num) {
		return itemMapper.getItem(num);
	}

}
