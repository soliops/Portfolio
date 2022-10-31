package com.tj.shopping.service;	

import java.util.List;

import com.tj.shopping.domain.CartDTO;

public interface CartService {
	public CartDTO getItem(String pidx);
	public CartDTO selectCart(String pidx);
	public List<CartDTO> getCartList();
	public List<CartDTO> getCart(String pidx);
	public void InsertCart(CartDTO cartDTO);
	public void deleteCart(String pidx);
	
}
