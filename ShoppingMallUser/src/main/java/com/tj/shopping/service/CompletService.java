package com.tj.shopping.service;

import java.util.Map;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;

public interface CompletService {
	public void insertOrder(CompletDTO completDTO);
	public ItemDTO getItem(String code);
	public String calDate(String date)throws Exception;
	public CompletDTO setCompletDTO(Map<String, String> resultMap);
}
