package com.tj.shopping.user.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {
	private String idx;
	private String notice_print;
	private String notice_title;
	private String notice_writer;
	private String notice_file;
	private String notice_text;
	private String notice_date;
	private int startpage;
	private int pageview;
	private int count;
	
}
