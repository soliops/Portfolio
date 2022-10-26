package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.ItemDTO;

@Repository
public interface OrderMapper {
	@Select("select * from product where product_code=#{product_code}")
	public ItemDTO getProduct(String product_code);
}
