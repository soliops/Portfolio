package com.tj.shopping.service;

import com.tj.shopping.domain.ItemDTO;

public interface OrderService {
	
	public ItemDTO getProduct(String product_code);
}
