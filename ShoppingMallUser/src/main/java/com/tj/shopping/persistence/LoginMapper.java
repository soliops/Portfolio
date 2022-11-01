package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.LogDTO;
import com.tj.shopping.domain.LoginDTO;

@Repository
public interface LoginMapper {

	@Select("select * from member where mid=#{mid} and mpassword=#{mpassword}")
	public LoginDTO getId(LoginDTO loginDTO);
	
	@Insert("insert into log_member values('0',#{mid},#{date})")
	public void insertHistory(LogDTO logDTO);
}
