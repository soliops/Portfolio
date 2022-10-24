package com.tj.shopping.domain;

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
}