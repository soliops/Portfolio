package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;

@Repository
public interface CompletMapper {
	
	@Insert("insert into orders "
			+ "values #{order_code},#{cname},#{chp},#{ctel},#{cemail},#{person_nm},"
			+ "#{person_post},#{person_addr},#{person_addrdtc},#{person_hp},#{person_htel},"
			+ "#{ship},#{ship_pay},#{ship_memo},#{product_idx},#{product_nm},#{product_code},"
			+ "#{product_ea},#{product_price},#{product_total},#{payment},#{regDate},#{modDate}")
	public void insertOrder(CompletDTO completDTO);
	
	@Select("select * from product where product_code=#{code}")
	public ItemDTO getProduct(String code);
	
	@Select("select product_stock from product where product_code=#{code}")
	public int getProductStock(String code);

}
