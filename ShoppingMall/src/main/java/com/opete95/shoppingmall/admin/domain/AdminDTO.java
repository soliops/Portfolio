package com.opete95.shoppingmall.admin.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	private String admin_id;
	private String admin_check;
	private String admin_log_id;
	private String admin_log_pw;
	private String admin_id_check;
	private String admin_pw;
	private String admin_pw2;
	private String admin_nm;
	private String admin_email;
	private String admin_tel1;
	private String admin_tel2;
	private String admin_tel3;
	private String admin_part;
	private String admin_position;




	
}
