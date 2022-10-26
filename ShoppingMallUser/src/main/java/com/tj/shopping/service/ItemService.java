package com.tj.shopping.service;

import java.util.List;

import com.tj.shopping.domain.ItemDTO;

public interface ItemService {
	public ItemDTO getItem(Integer num);
	public List<ItemDTO> getList(String cate);
	public ItemDTO getProduct(String product);
}
