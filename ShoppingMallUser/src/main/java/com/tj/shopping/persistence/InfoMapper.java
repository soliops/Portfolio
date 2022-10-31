package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.InfoDTO;

@Repository
public interface InfoMapper {
	
	@Select("select hcom_address,hrep_tel from homepage_config")
	public InfoDTO getInfo();
}
