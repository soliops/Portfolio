package com.tj.shopping.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tj.shopping.domain.NoticeDTO;

@Repository
public interface NoticeMapper {

	@Select("select * from admin_notice where notice_print='Y' and notice_title like concat('%',#{search},'%') order by idx desc")
	public List<NoticeDTO> getSearchList(String search);
	@Select("select count(*) as count from admin_notice where notice_print='N'")
	public Integer getcount();
	@Select("select * from admin_notice where notice_print=#{check} and notice_title like concat('%',#{search},'%') order by idx desc limit #{startpage}, #{pageview}")
	public List<NoticeDTO> getNoticeList(Map<String, Object> map);
}
