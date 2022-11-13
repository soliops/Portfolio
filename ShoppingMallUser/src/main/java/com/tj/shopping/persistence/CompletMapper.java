package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;

@Repository
public interface CompletMapper {
	
	@Insert("insert into orders "
			+ "values (#{order_code},#{mid},#{cname},#{chp},#{ctel},#{cemail},"
			+ "#{person_nm},#{person_post},#{person_addr},#{person_addrdtc},#{person_hp},#{person_htel},"
			+ "#{ship},#{ship_pay},#{ship_memo},#{ship_sum},"
			+ "#{product_idx},#{product_nm},#{product_code},"
			+ "#{product_ea},#{product_price},#{product_point},#{product_total},#{payment},#{bank_list},"
			+ "#{regDate},#{modDate})")
	public void insertOrder(CompletDTO completDTO);
	
	@Select("select * from product where product_code=#{code}")
	public ItemDTO getProduct(String code);
	
	@Select("select product_stock from product where product_code=#{code}")
	public int getProductStock(String code);

	@Update("update member set mpoint=mpoint+#{point}, mcount=mcount+1 where mid=#{mid}")
	public void updateOrderMember(@Param("point")String point,@Param("mid") String mid);
	
	@Update("update product set product_stock=product_stock-#{product_ea}, total_sales=total_sales+#{product_ea} where product_code=#{product_code}")
	public void updateProductStock(@Param("product_ea")String product_ea,@Param("product_code") String product_code);
	
	@Delete("delete from cart where product_code=#{product_code}")
	public void deleteCartOrder(String product_code);
}
