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
public class CompletDTO {
	private String cname;
	private String chp;
	private String ctel;
	private String cemail;
	private String person_nm;
	private String person_post;
	private String person_addr;
	private String person_addrdtc;
	private String person_hp;
	private String person_htel;
	
	private String order_code;
	private String ship;
	private String ship_pay;
	private String ship_memo;
	private String ship_sum;
	
	private String product_idx;
	private String product_nm;
	private String product_code;
	private String product_ea;
	private String product_price;
	private String product_point;
	private String payment;
	private String bank_list;
	
	private String product_total;
	private String regDate;
	private String modDate;
}
