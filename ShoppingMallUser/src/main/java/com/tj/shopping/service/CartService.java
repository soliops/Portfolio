package com.tj.shopping.service;

import java.util.List;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;

public interface CartService {
	public CartDTO getItem(String pidx);
	public List<CartDTO> getCartList();
	public void InsertCart(CartDTO cartDTO);
	public void deleteCart(String pidx);
}
