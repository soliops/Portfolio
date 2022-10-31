package com.tj.shopping.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;

@Repository
public interface OrderMapper {
	@Select("select * from cart where product_code=#{product_code}")
	public CartDTO getProduct(String product_code);
	
	@Select("select * from cart where product_idx=#{idx}")
	public List<CartDTO> getList(String idx);
	
	@Insert("insert into cart values (#{product_idx},"
			+ "#{product_nm},#{product_dtc},#{product_price},"
			+ "#{product_disprice},#{product_point},#{product_total},"
			+ "#{product_code},#{product_ea},#{product_stock},#{product_check},#{product_img1},#{indate},#{id_use},#{ship_pay})")
	public void InsertCart(CartDTO cartDTO);
	
	@Select("select * from product where pidx=#{pidx}")
	public ItemDTO getItem(String pidx);
}
