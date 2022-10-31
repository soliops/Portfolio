package com.tj.shopping.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.MemberDTO;

@Repository
public interface MemberMapper {

	@Insert("insert into member"
			+ " values(#{mid},#{mpassword},#{mname},"
			+ "#{memail},#{mtel},#{mpost},#{maddress1},#{maddress2},#{regdate},#{outdate},#{idsave}"
			+ ",#{mpoint},#{level},#{mcount})")
	public void insertMember(MemberDTO memberDTO); 
	@Select("select mid from member where mid=#{mid}")
	public MemberDTO selectId(String mid);
}
