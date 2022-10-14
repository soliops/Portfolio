package com.opete95.shoppingmall.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminNoticeDTO {
	private String notice_print;
	private String notice_title;
	private String notice_writer;
}
