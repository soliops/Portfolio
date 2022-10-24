package com.tj.shopping.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.CSDTO;

@Repository
public interface CSMapper {
	
	@Select("select * from faq_list where f_check='Y' limit 0,5")
	public List<CSDTO> getFAQ();
	
	
	@Select("select * from faq_list where f_check='Y' and fcategory=#{cate}")
	public List<CSDTO> selectFAQ(String cate);
}
