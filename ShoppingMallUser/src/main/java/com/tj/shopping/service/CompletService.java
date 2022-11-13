package com.tj.shopping.service;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;

public interface CompletService {
	public void insertOrder(CompletDTO completDTO);
	public void updateProductStock(String product_ea, String product_code);
	public void updateOrderMember(String point, String mid);
	public void deleteCartOrder(String product_code);
	public ItemDTO getItem(String code);
	public String calDate(String date)throws Exception;
	public String checkStock(String code, String ea);
}
