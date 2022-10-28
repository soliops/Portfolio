package com.tj.shopping.service;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;

public interface CompletService {
	public void insertOrder(CompletDTO completDTO);
	public ItemDTO getItem(String code);
	public String calDate(String date)throws Exception;
}
