package com.tj.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private String mtel;
	private String mpost;
	private String maddress1;
	private String maddress2;
	private String regdate;
	private String outdate;
	private String idsave;
	private String mpoint;
	private String level;
	private String mcount;
}
