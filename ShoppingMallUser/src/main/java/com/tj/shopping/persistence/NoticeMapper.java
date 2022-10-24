package com.tj.shopping.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.NoticeDTO;

@Repository
public interface NoticeMapper {

	@Select("select * from admin_notice where notice_print='Y' order by idx desc limit #{startpage},#{pageview}")
	public List<NoticeDTO> getList();
	@Select("select * from admin_notice where notice_print='Y' and notice_title=#{search} order by idx desc limit #{startpage},#{pageview}")
	public List<NoticeDTO> getSearchList(String search);
	@Select("select count(*) as count from admin_notice where notice_print='N'")
	public NoticeDTO getcount();
	@Select("select * from admin_notice where notice_print=#{check} and notice_title like CONCAT('%',#{search},'%') order by idx desc limit #{startpage},#{pageview}")
	public List<NoticeDTO> SearchList();
}
