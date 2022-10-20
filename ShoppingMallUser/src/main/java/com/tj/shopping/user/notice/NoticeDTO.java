package com.tj.shopping.user.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
	private String idx;
	private String notice_print;
	private String notice_title;
	private String notice_writer;
	private String notice_file;
	private String notice_text;
	private String notice_date;
	private Integer startpage;
	private Integer pageview;
	private Integer count;
	private String page;
	private String cate;
	private String search;
	private String p_check;
}