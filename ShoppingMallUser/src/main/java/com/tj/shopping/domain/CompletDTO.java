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

	private String ship;
	private String ship_pay;
	private String product_nm;
	private String product_color;
	private String product_ea;
	private String product_price;
	
	private String order_number;
	private String order_status;
	private String pay_status;
	private String payment;
	private String product_total;
}
