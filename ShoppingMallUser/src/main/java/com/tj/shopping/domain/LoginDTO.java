package com.tj.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private String mtel;
}
