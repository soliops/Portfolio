package com.tj.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.persistence.OrderMapper;

@Service
@Primary
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public ItemDTO getProduct(String product_code) {
		return orderMapper.getProduct(product_code);
	}

}
