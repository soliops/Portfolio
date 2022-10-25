package com.tj.shopping.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.ItemDTO;

@Repository
public interface ItemMapper {
	
	@Select("select * from product where pidx=#{num}")
	public ItemDTO getItem(Integer num);
	
	@Select("select * from product where cbcate_code=#{cate}")
	public List<ItemDTO> getList(String cate);
}
