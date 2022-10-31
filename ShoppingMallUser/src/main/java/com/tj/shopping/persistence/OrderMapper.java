package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CartDTO;

@Repository
public interface OrderMapper {
	@Select("select * from cart where product_code=#{product_code}")
	public CartDTO getProduct(String product_code);
}
