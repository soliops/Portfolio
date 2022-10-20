package com.tj.shopping.user.notice;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {

	@Select("select * from admin_notice where notice_print='Y' and notice_title=#{search} order by idx desc")
//	like CONCAT('%',#{search},'%')
	public List<NoticeDTO> getList(String search);
}
