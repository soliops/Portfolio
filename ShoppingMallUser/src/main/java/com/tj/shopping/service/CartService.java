package com.tj.shopping.service;	

import java.util.List;

import com.tj.shopping.domain.CartDTO;

public interface CartService {
	public CartDTO getItem(String pidx);
	public CartDTO selectCart(String pidx,String mid);
	public List<CartDTO> getCartList(String mid);
	public List<CartDTO> getCart(String pidx,String mid);
	public void InsertCart(CartDTO cartDTO);
	public void deleteCart(String pidx,String mid);
	
}
