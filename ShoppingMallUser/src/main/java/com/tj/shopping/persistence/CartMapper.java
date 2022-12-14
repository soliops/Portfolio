package com.tj.shopping.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;

@Repository
public interface CartMapper {
	@Select("select * from product where pidx=#{pidx}")
	public ItemDTO getItem(String pidx);
		
	@Select("select * from cart where mid=#{mid}")
	public List<CartDTO> getCartList(String mid);
	
	@Select("select * from cart where product_idx=#{product_idx} and mid=#{mid}")
	public List<CartDTO> selectCartList(@Param("product_idx") String product_idx,@Param("mid") String mid);
	
	@Select("select * from cart where product_idx=#{product_idx} and mid=#{mid}")
	public CartDTO selectCart(@Param("product_idx") String product_idx,@Param("mid") String mid);
	
	@Insert("insert into cart values (#{product_idx},"
			+ "#{product_nm},#{product_dtc},#{product_price},"
			+ "#{product_disprice},#{product_point},#{product_total},"
			+ "#{product_code},#{product_ea},#{product_stock},#{product_check},#{product_img1},#{indate},#{mid},#{id_use},#{ship_pay})")
	public void InsertCart(CartDTO cartDTO);
	
	@Delete("delete from cart where product_idx=#{product_idx} and mid=#{mid}")
	public void deleteCart(@Param("product_idx") String product_idx,@Param("mid") String mid);
	
	
}
