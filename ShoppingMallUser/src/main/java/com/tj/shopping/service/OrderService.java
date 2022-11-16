package com.tj.shopping.service;

import java.util.List;
import java.util.Map;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.OrderDTO;

public interface OrderService {
	public List<CartDTO> selectCart(String idx);
	public List<CartDTO> getProduct(String product_code,String ship_pay,String product_ea);
	public Map<String,String> getlist(OrderDTO dto,String orderNumber)throws Exception;
	public String orderNumber(String mid);
	public String getDate();
	public void InsertCart(OrderDTO orderDTO,String check,String Mid);
}
