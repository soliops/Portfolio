package com.tj.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CSDTO {
	private String fidx;
	private String fcategory;
	private String fname;
	private String f_qtext;
	private String f_atext;
	private String f_check;
	private String f_indate;
}
